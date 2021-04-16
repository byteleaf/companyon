import { Entity, ObjectID, ObjectIdColumn, Column } from 'typeorm';

@Entity()
export class HistorizedEntity<Entity> {
  @ObjectIdColumn()
  id: ObjectID;

  @Column()
  activeFrom: Date;

  @Column()
  entity: Entity;
}

@Entity()
export class User {
  @Column()
  id: string;

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
