import { Prop, Schema, SchemaFactory } from '@nestjs/mongoose';
import { Document } from 'mongoose';

export type HistorizedEntityDocument<Entity> = HistorizedEntity<Entity> & Document;

@Schema()
export class HistorizedEntity<Entity> {
  @Prop()
  activeFrom: Date;

  @Prop({ type: Object })
  entity: Entity;
}

export const HistorizedEntitySchema = SchemaFactory.createForClass(HistorizedEntity);
