import { Injectable } from '@nestjs/common';
import { InjectModel } from '@nestjs/mongoose';
import { Model } from 'mongoose';
import { HistorizationRepository } from 'src/historization/historization.repository';
import { UserInput } from 'src/users/User.input';
import { HistorizedUserEntity, HistorizedUserDocument, UserEntity } from 'src/users/User.schema';
import { User } from './User.dto';

@Injectable()
export class UsersService {
  usersRepository: HistorizationRepository<UserEntity, UserInput>;

  constructor(@InjectModel(HistorizedUserEntity.name) private userModel: Model<HistorizedUserDocument>) {
    this.usersRepository = new HistorizationRepository(this.userModel);
  }

  async currentUser(): Promise<User> {
    return {
      id: '1',
      firstName: 'Markus',
      lastName: 'Heer',
      email: 'markus.heer@byteleaf.de',
      admin: true,
    };
  }

  async findAll(): Promise<User[]> {
    const users = await this.usersRepository.findAll();

    return users.map((user) => new User(user));
  }

  async findOneById(id: string): Promise<User | null> {
    const user = await this.usersRepository.findOneById(id);

    return user ? new User(user) : user;
  }

  async create(userInput: UserInput): Promise<User> {
    const user = await this.usersRepository.create(userInput);

    return new User(user);
  }

  async update(userInput: UserInput, id: string): Promise<User> {
    return this.usersRepository.update(userInput, id);
  }

  async delete(id: string): Promise<User> {
    return this.usersRepository.delete(id);
  }
}
