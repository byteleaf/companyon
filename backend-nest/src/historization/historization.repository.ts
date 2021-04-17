import { Injectable } from '@nestjs/common';
import { Model } from 'mongoose';
import * as cuid from 'cuid';
import { HistorizedEntity, HistorizedEntityDocument } from 'src/historization/HistorizedEntity.schema';
import { InjectModel } from '@nestjs/mongoose';
import { HistorizedUserDocument } from 'src/users/User.schema';

@Injectable()
export class HistorizationRepository<Entity, EntityInput> {
  model: Model<HistorizedEntityDocument<any>>;

  constructor(@InjectModel(HistorizedEntity.name) model: Model<HistorizedEntityDocument<any>>) {
    this.model = model;
  }

  private createHistorizedEntity(entity: any): Promise<HistorizedEntityDocument<Entity>> {
    const id = entity.id || cuid();

    return this.model.create({
      id,
      active: true,
      activeFrom: Date.now(),
      entity: { ...entity, id },
    });
  }

  async findOneById(id: string): Promise<Entity | null> {
    const historizedEntity = await this.model.findOne({ id, active: true });

    return historizedEntity?.entity || null;
  }

  async findAll(): Promise<Entity[]> {
    const historizedEntities = await this.model.find({ active: true });

    return historizedEntities.map(({ entity }) => entity);
  }

  async create(entityInput: EntityInput): Promise<Entity> {
    const createdHistorizedEntity = await this.createHistorizedEntity(entityInput);

    return createdHistorizedEntity.entity;
  }

  async update(entityInput: EntityInput, id: string): Promise<Entity> {
    const historizedEntity = await this.model.findOne({ id, active: true });

    if (!historizedEntity) {
      throw new Error('historizedEntity not found');
    }

    historizedEntity.active = false;

    await historizedEntity.save();

    const updatedEntity = { ...historizedEntity?.entity, ...entityInput };

    const createdHistorizedEntity = await this.createHistorizedEntity(updatedEntity);

    return createdHistorizedEntity.entity;
  }

  async delete(id: string): Promise<Entity> {
    const historizedEntity = await this.model.findOne({ id, active: true });

    if (!historizedEntity) {
      throw new Error('historizedEntity not found');
    }

    historizedEntity.active = false;

    await historizedEntity.save();

    return historizedEntity.entity;
  }
}
