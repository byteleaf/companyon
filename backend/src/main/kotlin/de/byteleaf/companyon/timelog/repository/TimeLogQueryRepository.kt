package de.byteleaf.companyon.timelog.repository

import de.byteleaf.companyon.timelog.entity.TimeLogEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Criteria.where
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.isEqualTo
import org.springframework.stereotype.Repository
import java.time.OffsetDateTime
import java.util.*

@Repository
class TimeLogQueryRepository {

    @Autowired
    private lateinit var mongoTemplate: MongoTemplate

    fun findTimeLogs(from: OffsetDateTime?, to: OffsetDateTime?, user: String?, project: String?): List<TimeLogEntity> {
        val query = Query()
        if (user != null) query.addCriteria(where("user").isEqualTo(user))
        if (project != null) query.addCriteria(where("project").isEqualTo(project))
        appendFromToCriteria(query, from, to)
        return mongoTemplate.find(query, TimeLogEntity::class.java)
    }

    fun appendFromToCriteria(query: Query, from: OffsetDateTime?, to: OffsetDateTime?) {
        if (from != null && to != null) {
            query.addCriteria(
                Criteria().andOperator(
                    where("start").gte(Date.from(from.toInstant())),
                    where("start").lte(Date.from(to.toInstant()))
                )
            )
        } else if (from != null) {
            query.addCriteria(where("start").gte(Date.from(from.toInstant())))
        } else if (to != null) {
            query.addCriteria(where("start").lte(Date.from(to.toInstant())))
        }
    }
}
