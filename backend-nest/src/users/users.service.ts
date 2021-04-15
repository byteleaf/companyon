import { Injectable } from '@nestjs/common';
import { User } from './User';

@Injectable()
export class UsersService {
  async currentUser(): Promise<User> {
    return { id: '1', firstName: 'Markus', lastName: 'Heer', email: 'markus.heer@byteleaf.de', admin: true };
  }
}
