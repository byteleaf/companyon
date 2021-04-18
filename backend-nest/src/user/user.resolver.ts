import { UseGuards } from '@nestjs/common';
import { Resolver, Query, Mutation, Args } from '@nestjs/graphql';
import { CurrentUser } from 'src/auth/currentUser.decorator';
import { GqlAuthGuard } from 'src/auth/gqlAuth.guard';
import { UserEntity } from 'src/user/model/User.schema';
import { UserService } from 'src/user/user.service';
import { UserDTO } from './model/User.dto';
import { UserInput } from './model/User.input';

@Resolver(() => UserDTO)
@UseGuards(GqlAuthGuard)
export class UserResolver {
  constructor(private userService: UserService) {}

  @Query(() => UserDTO)
  async currentUser(@CurrentUser() user: UserEntity): Promise<UserDTO> {
    return new UserDTO(user);
  }

  @Query(() => UserDTO)
  async user(@Args('id') id: string): Promise<UserDTO | null> {
    return this.userService.findOneById(id);
  }

  @Query(() => [UserDTO])
  async users(): Promise<UserDTO[]> {
    return this.userService.findAll();
  }

  @Mutation(() => UserDTO)
  async createUser(@Args('input') user: UserInput): Promise<UserDTO> {
    return this.userService.create(user);
  }

  @Mutation(() => UserDTO)
  async updateUser(@Args('input') user: UserInput, @Args('id') id: string): Promise<UserDTO> {
    return this.userService.update(user, id);
  }

  @Mutation(() => UserDTO)
  async deleteUser(@Args('id') id: string): Promise<UserDTO> {
    return this.userService.delete(id);
  }
}
