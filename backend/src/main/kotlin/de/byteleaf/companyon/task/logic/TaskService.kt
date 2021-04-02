package de.byteleaf.companyon.task.logic

import de.byteleaf.companyon.common.entity.EntityType
import de.byteleaf.companyon.common.logic.AbstractEventDataService
import de.byteleaf.companyon.task.dto.Task
import de.byteleaf.companyon.task.dto.TaskInput
import de.byteleaf.companyon.task.dto.TaskUpdate
import de.byteleaf.companyon.task.entity.TaskEntity
import de.byteleaf.companyon.task.entity.TaskState
import de.byteleaf.companyon.task.repository.TaskQueryRepository
import de.byteleaf.companyon.task.repository.TaskRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class TaskService :
    AbstractEventDataService<
        TaskEntity, Task, TaskUpdate, TaskInput, TaskRepository>() {

    @Autowired private lateinit var taskQueryRepository: TaskQueryRepository

    override fun getEntityType(): EntityType = EntityType.TASK

    fun findTasks(user: String?, status: TaskState?): List<Task> =
        taskQueryRepository.findTasks(user, status).map { entityToOutput(it) }
}
