import { UseGuards } from '@nestjs/common';
import { Resolver, Query, Mutation, Args } from '@nestjs/graphql';
import { GqlAuthGuard } from 'src/auth/gqlAuth.guard';
import { UsersService } from 'src/users/users.service';
import { User } from './User.dto';
import { UserInput } from './User.input';

@Resolver(() => User)
export class UsersResolver {
  constructor(private usersService: UsersService) {}

  @Query(() => User)
  @UseGuards(GqlAuthGuard)
  async currentUser() {
    return this.usersService.currentUser();
  }

  @Query(() => [User])
  @UseGuards(GqlAuthGuard)
  async users() {
    return this.usersService.findAll();
  }

  @Mutation(() => User)
  async createUser(@Args('input') user: UserInput) {
    return this.usersService.create(user);
  }

  /* @Mutation(() => User)
  async updateUser(@Args('input') user: UserInput, @Args('id') id: string) {
    return this.usersService.update(user, id);
  } */
}
