import { Module } from '@nestjs/common';
import { MongooseModule } from '@nestjs/mongoose';
import { HistorizationRepository } from 'src/historization/historization.repository';
import { HistorizedEntity, HistorizedEntitySchema } from 'src/historization/HistorizedEntity.schema';

@Module({
  imports: [MongooseModule.forFeature([{ name: HistorizedEntity.name, schema: HistorizedEntitySchema }])],
  providers: [HistorizationRepository],
  exports: [HistorizationRepository],
})
export class HistorizationModule {}
