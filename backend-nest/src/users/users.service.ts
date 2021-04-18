import { Injectable } from '@nestjs/common';
import { HistorizationRepository } from 'src/historization/historization.repository';
import { UserDTO } from 'src/users/User.dto';
import { UserInput } from 'src/users/User.input';
import { UserEntity } from 'src/users/User.schema';

@Injectable()
export class UsersService {
  constructor(private userRepository: HistorizationRepository<UserEntity, UserInput>) {}

  async findAll(): Promise<UserDTO[]> {
    const users = await this.userRepository.findAll();

    return users.map((user) => new UserDTO(user));
  }

  async findOneById(id: string): Promise<UserDTO | null> {
    const user = await this.userRepository.findOneById(id);

    return user ? new UserDTO(user) : user;
  }

  async create(userInput: UserInput): Promise<UserDTO> {
    const user = await this.userRepository.create(userInput);

    return new UserDTO(user);
  }

  async update(userInput: UserInput, id: string): Promise<UserDTO> {
    return new UserDTO(await this.userRepository.update(userInput, id));
  }

  async delete(id: string): Promise<UserDTO> {
    return new UserDTO(await this.userRepository.delete(id));
  }
}
