import { Module } from '@nestjs/common';
import { PassportModule } from '@nestjs/passport';
import { HistorizationModule } from 'src/historization/historization.module';
import { HistorizedUserCollectionName, HistorizedUserSchema } from 'src/user/model/User.schema';
import { JwtStrategy } from './jwt.strategy';

@Module({
  imports: [
    PassportModule.register({ defaultStrategy: 'jwt' }),
    HistorizationModule.register({ name: HistorizedUserCollectionName, schema: HistorizedUserSchema }),
  ],
  providers: [JwtStrategy],
  exports: [PassportModule],
})
export class AuthModule {}
