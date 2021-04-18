import { Field, ObjectType } from '@nestjs/graphql';
import { UserEntity } from './User.schema';

@ObjectType()
export class Avatar {
  @Field()
  url: string;
}

@ObjectType('User')
export class UserDTO {
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

  sub: never;
  avatarUrl: never;

  constructor({ id, firstName, lastName, email, admin, avatarUrl }: UserEntity) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.admin = admin || false;
    this.avatar = avatarUrl ? { url: avatarUrl } : undefined;
  }
}
