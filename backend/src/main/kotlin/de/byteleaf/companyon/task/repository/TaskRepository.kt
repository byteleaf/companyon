package de.byteleaf.companyon.task.repository

import de.byteleaf.companyon.task.entity.TaskEntity
import org.springframework.data.mongodb.repository.MongoRepository

interface TaskRepository : MongoRepository<TaskEntity, String> {

    // TODO
    // @Query(:parameterOne is null or parameter1 = :parameterOne) and (:parameterTwo is null or parameter2 = :parameterTwo)
//    @Query(":user ")
//    fun findTasks(user: String?, status: TaskState?): List<TaskEntity>
}
