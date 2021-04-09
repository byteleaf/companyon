package de.byteleaf.companyon.absence.repository

import de.byteleaf.companyon.absence.constant.AbsenceType
import de.byteleaf.companyon.absence.constant.ApprovedQueryState
import de.byteleaf.companyon.absence.entity.AbsenceRequestEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
class AbsenceRequestQueryRepository {

    @Autowired
    private lateinit var mongoTemplate: MongoTemplate

    fun findAbsenceRequests(from: LocalDate?, to: LocalDate?, userIds: Collection<String>?, types: Collection<AbsenceType>?, approved: ApprovedQueryState): List<AbsenceRequestEntity> {
        val query = Query()
        if (from != null) query.addCriteria(Criteria.where("from").gte(from))
        if (to != null) query.addCriteria(Criteria.where("to").lte(to))
        if (!types.isNullOrEmpty()) query.addCriteria(Criteria.where("type").`in`(types))
        if (!userIds.isNullOrEmpty()) query.addCriteria(Criteria.where("user").`in`(userIds))
        if (approved == ApprovedQueryState.APPROVED) query.addCriteria(Criteria.where("approvedBy").ne(null))
        if (approved == ApprovedQueryState.NOT_APPROVED) {
            query.addCriteria(Criteria.where("approvedBy").`is`(null))
        }
        return mongoTemplate.find(query, AbsenceRequestEntity::class.java)
    }
}