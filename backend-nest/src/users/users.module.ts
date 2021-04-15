import { Module } from '@nestjs/common';
import { UsersResolver } from 'src/users/users.resolver';
import { UsersService } from './users.service';

@Module({
  imports: [],
  providers: [UsersService, UsersResolver],
  exports: [UsersService],
})
export class UsersModule {}
