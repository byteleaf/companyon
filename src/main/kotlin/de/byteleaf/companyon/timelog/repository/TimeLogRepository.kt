package de.byteleaf.companyon.timelog.repository

import de.byteleaf.companyon.timelog.entity.TimeLogEntity
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.data.repository.query.Param
import java.time.OffsetDateTime

interface TimeLogRepository : MongoRepository<TimeLogEntity, String> {

    @Query("""SELECT u FROM TimeLogEntity t WHERE
        (:from is NULL OR t.from >= :from)
        AND (:to is NULL OR t.to <= :to)
        AND (:user is NULL OR t.user = :user)
        AND (:project is NULL OR t.project = :project""")
    fun findTimeLogs(@Param("from") from: OffsetDateTime?, to: OffsetDateTime?, @Param("user") user: String?, @Param("project") project: String?): List<TimeLogEntity>
}