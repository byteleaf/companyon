import { Prop, Schema, SchemaFactory } from '@nestjs/mongoose';
import { Document } from 'mongoose';
import { HistorizedEntity } from 'src/historization/HistorizedEntity.schema';

export type HistorizedUserDocument = HistorizedUserEntity & Document;

@Schema()
export class UserEntity {
  @Prop()
  id: string;

  @Prop()
  sub?: string;

  @Prop()
  firstName: string;

  @Prop()
  lastName: string;

  @Prop()
  email: string;

  @Prop()
  admin: boolean;

  @Prop()
  avatarUrl?: string;
}

@Schema()
export class HistorizedUserEntity extends HistorizedEntity<UserEntity> {}

export const HistorizedUserCollectionName = 'HistorizedUser';

export const HistorizedUserSchema = SchemaFactory.createForClass(HistorizedUserEntity);
