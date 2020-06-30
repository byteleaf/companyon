package de.byteleaf.companyon.common.control

import de.byteleaf.companyon.common.entity.AbstractEntity
import de.byteleaf.companyon.common.util.GenericSupportUtil
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.repository.MongoRepository

@Suppress("SpringJavaInjectionPointsAutowiringInspection")
abstract class AbstractDataService<E: AbstractEntity, O, I, R: MongoRepository<E, Long>> {

    @Autowired
    protected lateinit var repository: R
    @Autowired
    protected lateinit var modelMapper: ModelMapper
    @Autowired
    private lateinit var idGeneratorService: IdGeneratorService

    private val POSITION_E = 0;
    private val POSITION_O = 1;
    private val genericSupportUtil = GenericSupportUtil()

    @Suppress("UNCHECKED_CAST")
    private fun inputToEntity(input: I): E = modelMapper.map(input, genericSupportUtil.getClassFromGeneric(this, POSITION_E) as Class<E>)
    @Suppress("UNCHECKED_CAST")
    private fun entityToOutput(entity: E): O = modelMapper.map(entity, genericSupportUtil.getClassFromGeneric(this, POSITION_O)) as O

    fun create(input: I): O {
        val entity= inputToEntity(input)
        entity.id = idGeneratorService.generateId()
        return entityToOutput(repository.insert(entity))
    }

    fun update(id: Long, input: I): O {
        val entity = inputToEntity(input)
        entity.id = id
        return entityToOutput(repository.save(entity))
    }

    fun get(id: Long): O = entityToOutput(repository.findById(id).get())

    fun delete(id: Long) = repository.deleteById(id)

    fun getAll() = repository.findAll().map { entityToOutput(it) }
}