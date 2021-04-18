import { DynamicModule } from '@nestjs/common';
import { Module } from '@nestjs/common';
import { MongooseModule } from '@nestjs/mongoose';
import { Schema } from 'mongoose';
import { HistorizationRepository } from 'src/historization/historization.repository';

@Module({})
export class HistorizationModule {
  static register(modelConfig: { name: string; schema: Schema }): DynamicModule {
    return {
      module: HistorizationModule,
      imports: [MongooseModule.forFeature([{ name: modelConfig.name, schema: modelConfig.schema }])],
      providers: [
        HistorizationRepository,
        {
          provide: 'modelConfig',
          useValue: modelConfig,
        },
      ],
      exports: [HistorizationRepository],
    };
  }
}
