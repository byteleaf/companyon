import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { ObjectID, Repository } from 'typeorm';
import { UserDTO } from './User.dto';
import { User } from './User.entity';
import { UserInput } from './User.input';

@Injectable()
export class UsersService {
  constructor(@InjectRepository(User) private usersRepository: Repository<User>) {}

  async currentUser(): Promise<UserDTO> {
    return {
      id: ObjectID.createFromTime(1000),
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
}
