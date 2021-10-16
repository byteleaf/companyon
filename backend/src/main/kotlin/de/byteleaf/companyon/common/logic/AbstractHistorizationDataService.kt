package de.byteleaf.companyon.common.logic

import de.byteleaf.companyon.common.entity.BaseEntity
import de.byteleaf.companyon.common.entity.EntityType
import org.springframework.data.mongodb.repository.MongoRepository

abstract class AbstractHistorizationDataService<E : BaseEntity, R : MongoRepository<E, String>> {


    // check if deleted

    // move into history table

    // check history table if newer version is available


    /**
     * The type of the entity to make event listener conditions possible
     */
    abstract fun getEntityType(): EntityType
}