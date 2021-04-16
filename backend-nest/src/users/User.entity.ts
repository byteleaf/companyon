import { Entity, ObjectID, ObjectIdColumn, Column } from 'typeorm';

@Entity()
export abstract class HistorizedEntity {
  @ObjectIdColumn()
  id: ObjectID;

  @Column()
  activeFrom: Date;

  @Column()
  entity: any;
}

@Entity()
export class User {
  @Column()
  sub?: string;

  @Column()
  firstName: string;

  @Column()
  lastName: string;

  @Column()
  email: string;

  @Column()
  admin: boolean;

  @Column()
  avatarUrl?: string;
}

@Entity()
export class HistorizedUser extends HistorizedEntity {
  @ObjectIdColumn()
  id: ObjectID;

  @Column()
  activeFrom: Date;

  @Column()
  entity: User;
}
