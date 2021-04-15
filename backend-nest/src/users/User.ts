import { Field, ObjectType } from '@nestjs/graphql';

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

  @Field({ nullable: true })
  avatar?: Avatar;
}
