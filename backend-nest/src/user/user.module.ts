import { Module } from '@nestjs/common';
import { HistorizationModule } from 'src/historization/historization.module';
import { HistorizedUserCollectionName, HistorizedUserSchema } from 'src/user/model/User.schema';
import { UserResolver } from 'src/user/user.resolver';
import { UserService } from './user.service';

@Module({
  imports: [HistorizationModule.register({ name: HistorizedUserCollectionName, schema: HistorizedUserSchema })],
  providers: [UserService, UserResolver],
  exports: [UserService],
})
export class UserModule {}
