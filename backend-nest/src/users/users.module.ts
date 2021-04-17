import { Module } from '@nestjs/common';
import { MongooseModule } from '@nestjs/mongoose';
import { HistorizationModule } from 'src/historization/historization.module';
import { HistorizedUserEntity, HistorizedUserSchema } from 'src/users/User.schema';
import { UsersResolver } from 'src/users/users.resolver';
import { UsersService } from './users.service';

@Module({
  imports: [
    MongooseModule.forFeature([{ name: HistorizedUserEntity.name, schema: HistorizedUserSchema }]),
    HistorizationModule,
  ],
  providers: [UsersService, UsersResolver],
  exports: [UsersService],
})
export class UsersModule {}
