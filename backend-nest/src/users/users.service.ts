import { Injectable } from '@nestjs/common';
import { UserRepository } from 'src/users/user.repository';
import { ObjectID } from 'typeorm';
import { UserDTO } from './User.dto';
import { UserInput } from './User.input';

@Injectable()
export class UsersService {
  constructor(private usersRepository: UserRepository) {}

  async currentUser(): Promise<UserDTO> {
    return {
      id: '1',
      firstName: 'Markus',
      lastName: 'Heer',
      email: 'markus.heer@byteleaf.de',
      admin: true,
    };
  }

  async findAll(): Promise<UserDTO[]> {
    const users = await this.usersRepository.find();

    return users.map((user) => new UserDTO(user));
  }

  async create(user: UserInput): Promise<UserDTO> {
    const newUser = await this.usersRepository.save(user);

    return new UserDTO(newUser);
  }

  async update(user: UserInput, id: string): Promise<UserDTO> {
    await this.usersRepository.update(id, user);

    const updatedUser = await this.usersRepository.findOneOrFail(id);

    return new UserDTO(updatedUser);
  }
}
