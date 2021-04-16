import { UseGuards } from '@nestjs/common';
import { Resolver, Query, Mutation, Args } from '@nestjs/graphql';
import { GqlAuthGuard } from 'src/auth/gqlAuth.guard';
import { UsersService } from 'src/users/users.service';
import { UserDTO } from './User.dto';
import { UserInput } from './User.input';

@Resolver(() => UserDTO)
export class UsersResolver {
  constructor(private usersService: UsersService) {}

  @Query(() => UserDTO)
  @UseGuards(GqlAuthGuard)
  async currentUser() {
    return this.usersService.currentUser();
  }

  @Query(() => [UserDTO])
  @UseGuards(GqlAuthGuard)
  async users() {
    return this.usersService.findAll();
  }

  @Mutation(() => UserDTO)
  async createUser(@Args('input') user: UserInput) {
    return this.usersService.create(user);
  }

  @Mutation(() => UserDTO)
  async updateUser(@Args('input') user: UserInput, @Args('id') id: string) {
    return this.usersService.update(user, id);
  }
}
