package de.byteleaf.companyon.task.boundary

import de.byteleaf.companyon.task.access.TaskAccessService
import de.byteleaf.companyon.task.dto.Task
import de.byteleaf.companyon.task.dto.TaskInput
import de.byteleaf.companyon.task.dto.TaskUpdate
import de.byteleaf.companyon.task.entity.TaskState
import graphql.kickstart.tools.GraphQLMutationResolver
import graphql.kickstart.tools.GraphQLQueryResolver
import graphql.kickstart.tools.GraphQLSubscriptionResolver
import org.reactivestreams.Publisher
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller

@Controller
class TaskResolver : GraphQLQueryResolver, GraphQLMutationResolver, GraphQLSubscriptionResolver {

    @Autowired private lateinit var taskAccessService: TaskAccessService

    fun getTask(id: String): Task = taskAccessService.get(id)

    fun getTasks(user: String?, status: TaskState?): List<Task> = taskAccessService.findAll(user, status)

    fun createTask(input: TaskInput): Task = taskAccessService.create(input)

    fun updateTask(id: String, input: TaskInput): Task = taskAccessService.update(id, input)

    fun deleteTask(id: String): Task = taskAccessService.delete(id)

    fun taskUpdate(): Publisher<TaskUpdate> = taskAccessService.getPublisher()
}
