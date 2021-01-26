package de.byteleaf.companyon.timelog.repository

import de.byteleaf.companyon.timelog.entity.TimeLogEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.isEqualTo
import org.springframework.stereotype.Repository
import java.time.OffsetDateTime

@Repository
class TimeLogQueryRepository {

    @Autowired
    private lateinit var mongoTemplate: MongoTemplate

    fun findTimeLogs(from: OffsetDateTime?, to: OffsetDateTime?, user: String?, project: String?): List<TimeLogEntity> {
        val query = Query()
        if(from != null) query.addCriteria(Criteria.where("from").gte(from))
        if(to != null) query.addCriteria(Criteria.where("to").lte(to))
        if(user != null) query.addCriteria(Criteria.where("user").isEqualTo(user))
        if(project != null) query.addCriteria(Criteria.where("project").isEqualTo(project))
        return mongoTemplate.find(query, TimeLogEntity::class.java)
    }
}