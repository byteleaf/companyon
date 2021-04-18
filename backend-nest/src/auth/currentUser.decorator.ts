import { createParamDecorator, ExecutionContext } from '@nestjs/common';
import { GqlExecutionContext } from '@nestjs/graphql';
import { UserEntity } from 'src/users/User.schema';

export const CurrentUser = createParamDecorator(
  (data: unknown, context: ExecutionContext): UserEntity => {
    const ctx = GqlExecutionContext.create(context);

    return ctx.getContext().req.user as UserEntity;
  },
);
