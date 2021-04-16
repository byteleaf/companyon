import { HistorizedEntity, HistorizedUser, User } from 'src/users/User.entity';
import {
  DeepPartial,
  EntityManager,
  EntityRepository,
  EntityTarget,
  ObjectID,
  SaveOptions,
  UpdateResult,
} from 'typeorm';
import { QueryDeepPartialEntity } from 'typeorm/query-builder/QueryPartialEntity';

@EntityRepository()
export class HistorizationRepository<Entity> {
  entityClass: EntityTarget<HistorizedEntity>;
  private manager: EntityManager;

  constructor(manager: EntityManager, entityClass: EntityTarget<HistorizedEntity>) {
    this.manager = manager;
    this.entityClass = entityClass;
  }

  async find(): Promise<Entity[]> {
    const results = await this.manager.find(this.entityClass);
    return results.map(({ entity }) => entity);
  }

  async findOneOrFail(id: string | number | Date | ObjectID): Promise<Entity> {
    const restult = await this.manager.findOneOrFail(this.entityClass, id);
    return restult.entity;
  }

  async save<T extends DeepPartial<Entity>>(entity: T, options?: SaveOptions): Promise<Entity> {
    const createdEntity = this.manager.create(this.entityClass, {
      entity,
      activeFrom: new Date(),
    });

    const result = await this.manager.save(createdEntity, options);

    return result.entity;
  }

  async update(
    criteria: string | string[] | number | number[] | Date | Date[] | ObjectID | ObjectID[] | any,
    partialEntity: QueryDeepPartialEntity<Entity>,
  ): Promise<UpdateResult> {
    return this.manager.update(this.entityClass, criteria, partialEntity);
  }
}

@EntityRepository(User)
export class UserRepository extends HistorizationRepository<User> {
  constructor(manager: EntityManager) {
    super(manager, HistorizedUser);
  }
}
