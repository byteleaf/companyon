import { Injectable } from '@nestjs/common';
import { HistorizationRepository } from 'src/historization/historization.repository';
import { UserInput } from 'src/users/User.input';
import { UserEntity } from 'src/users/User.schema';
import { User } from './User.dto';

@Injectable()
export class UsersService {
  constructor(private userRepository: HistorizationRepository<UserEntity, UserInput>) {}

  async findAll(): Promise<User[]> {
    const users = await this.userRepository.findAll();

    return users.map((user) => new User(user));
  }

  async findOneById(id: string): Promise<User | null> {
    const user = await this.userRepository.findOneById(id);

    return user ? new User(user) : user;
  }

  async create(userInput: UserInput): Promise<User> {
    const user = await this.userRepository.create(userInput);

    return new User(user);
  }

  async update(userInput: UserInput, id: string): Promise<User> {
    return new User(await this.userRepository.update(userInput, id));
  }

  async delete(id: string): Promise<User> {
    return new User(await this.userRepository.delete(id));
  }
}
