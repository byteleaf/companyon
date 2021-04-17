import { Injectable } from '@nestjs/common';
import { InjectModel } from '@nestjs/mongoose';
import { Model } from 'mongoose';
import { UserInput } from 'src/users/User.input';
import { UserEntity, UserDocument } from 'src/users/User.schema';
import { User } from './User.dto';

@Injectable()
export class UsersService {
  constructor(@InjectModel(UserEntity.name) private userModel: Model<UserDocument>) {}

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
    const users = await this.userModel.find();

    return users.map((user) => new User(user));
  }

  async create(userInput: UserInput): Promise<User> {
    const createdUser = await this.userModel.create(userInput);

    return new User(createdUser);
  }

  async update(userInput: UserInput, id: string): Promise<User> {
    await this.userModel.updateOne({ _id: id }, { $set: userInput });

    const user = await this.userModel.findById(id);

    if (!user) {
      // TODO: Apollo Error handling
      throw new Error('user not found');
    }

    return new User(user);
  }
}
