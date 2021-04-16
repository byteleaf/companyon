import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { HistorizedEntity, User } from 'src/users/User.entity';
import { UserRepository } from 'src/users/user.repository';
import { UsersResolver } from 'src/users/users.resolver';
import { UsersService } from './users.service';

@Module({
  imports: [TypeOrmModule.forFeature([HistorizedEntity])],
  providers: [UsersService, UsersResolver, UserRepository],
  exports: [UsersService],
})
export class UsersModule {}
