import { Injectable } from '@nestjs/common';
import { HistorizationRepository } from 'src/historization/historization.repository';
import { UserDTO } from 'src/user/model/User.dto';
import { UserInput } from 'src/user/model/User.input';
import { UserEntity } from 'src/user/model/User.schema';

@Injectable()
export class UserService {
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
