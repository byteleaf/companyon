import { Field, ObjectType } from '@nestjs/graphql';
import { UserDocument } from './User.schema';

@ObjectType()
export class Avatar {
  @Field()
  url: string;
}

@ObjectType()
export class User {
  @Field()
  id: string;

  @Field()
  firstName: string;

  @Field()
  lastName: string;

  @Field()
  email: string;

  @Field()
  admin: boolean;

  @Field(() => Avatar, { nullable: true })
  avatar?: Avatar;

  constructor({ _id, firstName, lastName, email, admin, avatarUrl }: UserDocument) {
    this.id = _id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.admin = admin || false;
    this.avatar = avatarUrl ? { url: avatarUrl } : undefined;
  }
}
