import { Injectable } from '@nestjs/common';
import { Model } from 'mongoose';
import * as cuid from 'cuid';
import { HistorizedEntity, HistorizedEntityDocument } from 'src/historization/HistorizedEntity.schema';
import { InjectModel } from '@nestjs/mongoose';

@Injectable()
export class HistorizationRepository<Entity, EntityInput> {
  model: Model<HistorizedEntityDocument<Entity>>;

  constructor(@InjectModel(HistorizedEntity.name) model: Model<HistorizedEntityDocument<Entity>>) {
    this.model = model;
  }

  async findAll(): Promise<Entity[]> {
    const historizedEntities = await this.model.find();

    return historizedEntities.map(({ entity }) => entity);
  }

  async create(entityInput: EntityInput): Promise<Entity> {
    const createdHistorizedUser = await this.model.create({
      activeFrom: Date.now(),
      entity: { ...entityInput, id: cuid() },
    });

    return createdHistorizedUser.entity;
  }

  /* async update(entityInput: EntityInput, id: string): Promise<Entity> {
    await this.model.updateOne({ _id: id }, { $set: { entity: entityInput } });

    const historizedEntity = await this.model.findById(id);

    if (!historizedEntity) {
      // TODO: Apollo Error handling
      throw new Error('historizedEntity not found');
    }

    return historizedEntity.entity;
  } */
}
