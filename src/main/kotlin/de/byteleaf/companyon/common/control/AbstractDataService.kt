package de.byteleaf.companyon.common.control

import de.byteleaf.companyon.common.dto.BaseDTO
import de.byteleaf.companyon.common.entity.BaseEntity
import de.byteleaf.companyon.common.entity.EntityType
import de.byteleaf.companyon.common.error.exception.EntityNotFoundException
import de.byteleaf.companyon.common.event.EntityDeletedEvent
import de.byteleaf.companyon.common.util.GenericSupportUtil
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationEventPublisher
import org.springframework.data.mongodb.repository.MongoRepository

@Suppress("SpringJavaInjectionPointsAutowiringInspection")
abstract class AbstractDataService<E : BaseEntity, O : BaseDTO, I, R : MongoRepository<E, String>> {

    @Autowired
    protected lateinit var repository: R

    @Autowired
    protected lateinit var modelMapper: ModelMapper

    @Autowired
    protected lateinit var applicationEventPublisher: ApplicationEventPublisher

    private val POSITION_ENTITY = 0
    private val POSITION_OUTPUT_DTO = 1

    @Suppress("UNCHECKED_CAST")
    protected open fun inputToEntity(input: I): E = modelMapper.map(input, GenericSupportUtil.getClassFromGeneric(this, POSITION_ENTITY) as Class<E>)

    @Suppress("UNCHECKED_CAST")
    protected fun entityToOutput(entity: E): O = modelMapper.map(entity, GenericSupportUtil.getClassFromGeneric(this, POSITION_OUTPUT_DTO)) as O

    fun create(input: I): O = entityToOutput(repository.insert(inputToEntity(input)))

    fun update(id: String, input: I): O {
        val entity = inputToEntity(input)
        entity.id = id // make sure the url id will be used!
        return entityToOutput(repository.save(entity))
    }

    fun getEntity(id: String): E = repository.findById(id).orElseThrow { EntityNotFoundException(id, getEntityType()) }

    fun get(id: String): O = repository.findById(id).map { entityToOutput(it) }
            .orElseThrow { EntityNotFoundException(id, getEntityType()) }

    fun delete(id: String): O {
        val dto = get(id)
        repository.deleteById(id)
        applicationEventPublisher.publishEvent(EntityDeletedEvent(getEntityType(), dto))
        return dto
    }

    fun findAll() = repository.findAll().map { entityToOutput(it) }

    fun deleteAll() = repository.deleteAll()

    /**
     * The type of the entity to make event listener conditions possible
     */
    abstract fun getEntityType() : EntityType
}
