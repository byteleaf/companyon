import { Field, ObjectType } from '@nestjs/graphql';
import { ObjectID } from 'typeorm';
import { User } from './User.entity';

@ObjectType()
export class UserDTO {
  @Field(() => String)
  id: ObjectID;

  @Field()
  firstName: string;

  @Field()
  lastName: string;

  @Field()
  email: string;

  @Field()
  admin: boolean;

  @Field({ nullable: true })
  avatarUrl?: string;

  constructor({ id, firstName, lastName, email, admin, avatarUrl }: User) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.admin = admin || false;
    this.avatarUrl = avatarUrl;
  }
}
