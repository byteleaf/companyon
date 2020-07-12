package de.byteleaf.companyon.common.control

import de.byteleaf.companyon.common.entity.BaseEntity
import de.byteleaf.companyon.common.util.GenericSupportUtil
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.repository.MongoRepository

@Suppress("SpringJavaInjectionPointsAutowiringInspection")
abstract class AbstractDataService<E : BaseEntity, O, I, R : MongoRepository<E, String>> {

    @Autowired
    protected lateinit var repository: R

    @Autowired
    protected lateinit var modelMapper: ModelMapper

    private val POSITION_E = 0
    private val POSITION_O = 1

    @Suppress("UNCHECKED_CAST")
    private fun inputToEntity(input: I): E = modelMapper.map(input, GenericSupportUtil.getClassFromGeneric(this, POSITION_E) as Class<E>)

    @Suppress("UNCHECKED_CAST")
    private fun entityToOutput(entity: E): O = modelMapper.map(entity, GenericSupportUtil.getClassFromGeneric(this, POSITION_O)) as O

    open fun create(input: I): O = entityToOutput(repository.insert(inputToEntity(input)))

    open fun update(id: String, input: I): O {
        val entity = inputToEntity(input)
        entity.id = id
        return entityToOutput(repository.save(entity))
    }

    fun get(id: String): O = entityToOutput(repository.findById(id).get())

    open fun delete(id: String) = repository.deleteById(id)

    fun findAll() = repository.findAll().map { entityToOutput(it) }

    open fun deleteAll() = repository.deleteAll()
}
