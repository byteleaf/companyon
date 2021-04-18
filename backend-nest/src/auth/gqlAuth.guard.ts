import { ExecutionContext, Injectable } from '@nestjs/common';
import { GqlExecutionContext } from '@nestjs/graphql';
import { AuthGuard } from '@nestjs/passport';
import fetch from 'node-fetch';
import jwt_decode from 'jwt-decode';
import { AuthUser } from 'src/auth/types/AuthUser';
import { HistorizationRepository } from 'src/historization/historization.repository';
import { UserEntity } from 'src/users/User.schema';
import { AuthenticationError } from 'apollo-server-express';

@Injectable()
export class GqlAuthGuard extends AuthGuard('jwt') {
  constructor(private userRepository: HistorizationRepository<UserEntity, UserEntity>) {
    super();
  }

  getRequest(context: ExecutionContext): any {
    const ctx = GqlExecutionContext.create(context);

    return ctx.getContext().req;
  }

  async canActivate(context: ExecutionContext): Promise<boolean> {
    const Authorization = this.getRequest(context).headers.authorization;

    if (!Authorization) throw new AuthenticationError('No token was provided');

    const decoded: AuthUser | undefined = jwt_decode(Authorization.replace('Bearer ', ''));

    if (!decoded || !decoded.sub) throw new AuthenticationError('Token has invalid structure');

    const userBySub = await this.userRepository.findOne({ 'entity.sub': decoded.sub });

    if (!userBySub) {
      const userInfoUrl = `${process.env.AUTH0_ISSUER_URL}userinfo`;

      const req = await fetch(userInfoUrl, { headers: { Authorization } }).then((res) => res.json());

      if (req && req.email) {
        const userByEmail = await this.userRepository.findOne({ 'entity.email': req.email });

        if (!userByEmail) throw new AuthenticationError('User does not exist in database');

        const updatedUser = await this.userRepository.update(
          { ...userByEmail, sub: req.sub, avatarUrl: req.picture },
          userByEmail.id,
        );

        this.getRequest(context).user = updatedUser;

        return true;
      }

      throw new AuthenticationError('userinfo response malformed');
    }

    this.getRequest(context).user = userBySub;

    return true;
  }
}
