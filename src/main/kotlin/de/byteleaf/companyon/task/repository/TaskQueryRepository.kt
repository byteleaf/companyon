package de.byteleaf.companyon.task.repository

import de.byteleaf.companyon.task.entity.TaskEntity
import de.byteleaf.companyon.task.entity.TaskState
import java.util.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria.where
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.isEqualTo
import org.springframework.stereotype.Repository

@Repository
class TaskQueryRepository {

    @Autowired private lateinit var mongoTemplate: MongoTemplate

    fun findTasks(user: String?, status: TaskState?): List<TaskEntity> {
        val query = Query()
        if (status != null) query.addCriteria(where("status").isEqualTo(status))
        if (user != null) query.addCriteria(where("user").isEqualTo(user))
        return mongoTemplate.find(query, TaskEntity::class.java)
    }
}
