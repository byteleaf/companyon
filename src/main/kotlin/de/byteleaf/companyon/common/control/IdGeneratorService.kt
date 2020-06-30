package de.byteleaf.companyon.common.control

import de.byteleaf.companyon.common.entity.IdGeneratorEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.FindAndModifyOptions.options
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.query.Criteria.where
import org.springframework.data.mongodb.core.query.Query.query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.stereotype.Service
import java.util.*

@Service
class IdGeneratorService {

    @Autowired
    private lateinit var mongoOperations: MongoOperations

    fun generateId(): Long {
        val counter: IdGeneratorEntity? = mongoOperations.findAndModify(query(where("_id").`is`("ID_GENERATOR")),
                Update().inc("seq", 1), options().returnNew(true).upsert(true),
                IdGeneratorEntity::class.java)
        return if (!Objects.isNull(counter)) counter!!.seq else 1
    }
}