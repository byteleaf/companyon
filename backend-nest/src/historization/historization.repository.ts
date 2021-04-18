import { Inject, Injectable } from '@nestjs/common';
import { Connection, FilterQuery, Model, Schema } from 'mongoose';
import * as cuid from 'cuid';
import { HistorizedEntityDocument } from 'src/historization/HistorizedEntity.schema';
import { InjectConnection } from '@nestjs/mongoose';

@Injectable()
export class HistorizationRepository<Entity, EntityInput> {
  private model: Model<HistorizedEntityDocument<any>>;

  constructor(
    @InjectConnection() private connection: Connection,
    @Inject('modelConfig') private modelConfig: { name: string; schema: Schema },
  ) {
    this.model = connection.model<HistorizedEntityDocument<any>>(
      modelConfig.name,
      modelConfig.schema,
      modelConfig.name,
    );
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

  async findOne(filter: FilterQuery<Entity>): Promise<Entity | null> {
    const historizedEntity = await this.model.findOne({ ...filter, active: true });

    return historizedEntity?.entity || null;
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
