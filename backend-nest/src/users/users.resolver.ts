import { UseGuards } from '@nestjs/common';
import { Resolver, Query, Mutation, Args } from '@nestjs/graphql';
import { CurrentUser } from 'src/auth/currentUser.decorator';
import { GqlAuthGuard } from 'src/auth/gqlAuth.guard';
import { UserEntity } from 'src/users/User.schema';
import { UsersService } from 'src/users/users.service';
import { UserDTO } from './User.dto';
import { UserInput } from './User.input';

@Resolver(() => UserDTO)
@UseGuards(GqlAuthGuard)
export class UsersResolver {
  constructor(private usersService: UsersService) {}

  @Query(() => UserDTO)
  async currentUser(@CurrentUser() user: UserEntity): Promise<UserDTO> {
    return new UserDTO(user);
  }

  @Query(() => UserDTO)
  async user(@Args('id') id: string): Promise<UserDTO | null> {
    return this.usersService.findOneById(id);
  }

  @Query(() => [UserDTO])
  async users(): Promise<UserDTO[]> {
    return this.usersService.findAll();
  }

  @Mutation(() => UserDTO)
  async createUser(@Args('input') user: UserInput): Promise<UserDTO> {
    return this.usersService.create(user);
  }

  @Mutation(() => UserDTO)
  async updateUser(@Args('input') user: UserInput, @Args('id') id: string): Promise<UserDTO> {
    return this.usersService.update(user, id);
  }

  @Mutation(() => UserDTO)
  async deleteUser(@Args('id') id: string): Promise<UserDTO> {
    return this.usersService.delete(id);
  }
}
