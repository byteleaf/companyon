import { Field, ObjectType } from '@nestjs/graphql';
import { User } from './User.entity';

@ObjectType()
export class UserDTO {
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

  constructor({ firstName, lastName, email, admin, avatarUrl }: User) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.admin = admin || false;
    this.avatarUrl = avatarUrl;
  }
}
