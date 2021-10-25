package de.byteleaf.companyon.common.repository

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Repository
import java.time.OffsetDateTime
import java.util.*

@Repository
class HistoryQueryRepository {


    // TODO Check during creation that there is no entity with identical validFrom -> throw error

    @Autowired
    private lateinit var mongoTemplate: MongoTemplate

    // TODO test
    fun <Entity> findNewerVersions(cls: Class<Entity>, currentMoment: OffsetDateTime, entities: List<Pair<out String, out OffsetDateTime>>): List<Entity> {
        val query = Query()
        entities.takeIf { !it.isEmpty() } ?: throw Exception("Argument list should not be empty")

        entities.forEach {
            query.addCriteria(
                Criteria().andOperator(
                    Criteria.where("entityId").`is`(it.first),
                    Criteria.where("validFrom").gt(Date.from(it.second.toInstant())),
                    Criteria.where("validFrom").lt(Date.from(currentMoment.toInstant())),
                )
            )
        }

        return mongoTemplate.find(query, cls)
    }
}