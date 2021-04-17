import { Prop, Schema, SchemaFactory } from '@nestjs/mongoose';
import { Document } from 'mongoose';

export type UserDocument = UserEntity & Document;

@Schema()
export class UserEntity {
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

export const UserSchema = SchemaFactory.createForClass(UserEntity);
