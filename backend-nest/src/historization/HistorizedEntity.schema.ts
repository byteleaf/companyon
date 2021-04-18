import { Prop, Schema } from '@nestjs/mongoose';
import { Document } from 'mongoose';

export type HistorizedEntityDocument<Entity> = HistorizedEntity<Entity> & Document;

@Schema()
export class HistorizedEntity<Entity> {
  @Prop()
  id: string;

  @Prop()
  active: boolean;

  @Prop()
  activeFrom: Date;

  @Prop({ type: Object })
  entity: Entity;
}
