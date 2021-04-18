import { UseGuards } from '@nestjs/common';
import { Resolver, Query, Mutation, Args } from '@nestjs/graphql';
import { CurrentUser } from 'src/auth/currentUser.decorator';
import { GqlAuthGuard } from 'src/auth/gqlAuth.guard';
import { UserEntity } from 'src/users/User.schema';
import { UsersService } from 'src/users/users.service';
import { User } from './User.dto';
import { UserInput } from './User.input';

@Resolver(() => User)
export class UsersResolver {
  constructor(private usersService: UsersService) {}

  @Query(() => User)
  @UseGuards(GqlAuthGuard)
  async currentUser(@CurrentUser() user: UserEntity): Promise<User> {
    return new User(user);
  }

  @Query(() => User)
  @UseGuards(GqlAuthGuard)
  async user(@Args('id') id: string): Promise<User | null> {
    return this.usersService.findOneById(id);
  }

  @Query(() => [User])
  @UseGuards(GqlAuthGuard)
  async users(): Promise<User[]> {
    return this.usersService.findAll();
  }

  @Mutation(() => User)
  @UseGuards(GqlAuthGuard)
  async createUser(@Args('input') user: UserInput): Promise<User> {
    return this.usersService.create(user);
  }

  @Mutation(() => User)
  @UseGuards(GqlAuthGuard)
  async updateUser(@Args('input') user: UserInput, @Args('id') id: string): Promise<User> {
    return this.usersService.update(user, id);
  }

  @Mutation(() => User)
  @UseGuards(GqlAuthGuard)
  async deleteUser(@Args('id') id: string): Promise<User> {
    return this.usersService.delete(id);
  }
}
