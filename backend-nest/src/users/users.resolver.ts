import { UseGuards } from '@nestjs/common';
import { Resolver, Query } from '@nestjs/graphql';
import { GqlAuthGuard } from 'src/auth/gqlAuth.guard';
import { UsersService } from 'src/users/users.service';
import { User } from './User';

@Resolver(() => User)
export class UsersResolver {
  constructor(private usersService: UsersService) {}

  @Query(() => User)
  @UseGuards(GqlAuthGuard)
  async currentUser() {
    return this.usersService.currentUser();
  }
}
