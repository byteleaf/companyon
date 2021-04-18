import { Module } from '@nestjs/common';
import { HistorizationModule } from 'src/historization/historization.module';
import { HistorizedUserEntity, HistorizedUserSchema } from 'src/users/User.schema';
import { UsersResolver } from 'src/users/users.resolver';
import { UsersService } from './users.service';

@Module({
  imports: [HistorizationModule.register({ name: HistorizedUserEntity.name, schema: HistorizedUserSchema })],
  providers: [UsersService, UsersResolver],
  exports: [UsersService],
})
export class UsersModule {}
