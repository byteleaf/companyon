package de.byteleaf.companyon.task

import de.byteleaf.companyon.auth.configuration.NonSecConfiguration
import de.byteleaf.companyon.task.dto.TaskGQLResponse
import de.byteleaf.companyon.task.entity.TaskState
import de.byteleaf.companyon.task.logic.TaskService
import de.byteleaf.companyon.test.AbstractQueryMutationIT
import de.byteleaf.companyon.user.logic.UserService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class TaskIT : AbstractQueryMutationIT("task") {

    private val targetClass = TaskGQLResponse::class.java

    @Autowired
    private lateinit var taskService: TaskService

    @Autowired
    private lateinit var userService: UserService

    @BeforeEach
    fun init() {
        userService.deleteAll(NonSecConfiguration.NON_SEC_USER_ID)
        taskService.deleteAll()
    }

    @Test
    fun create() {
        val createdEntity = createTask()
        assertThat(createdEntity.description).isEqualTo("test task")
        assertThat(createdEntity.status).isEqualTo(TaskState.OPEN)
        assertThat(createdEntity.user!!.firstName).isEqualTo(NonSecConfiguration.NON_SEC_FIRST_NAME)
    }

    @Test
    fun delete() {
        val createdEntity = createTask()
        assertThat(taskService.findAll().size).isEqualTo(1)
        performGQLById("DeleteTask", createdEntity.id!!)
        assertThat(taskService.findAll().size).isEqualTo(0)
    }

    @Test
    fun update() {
        val createdEntity = createTask()
        val updatedEntity = performGQLByIdAndInput(
            "UpdateTask", createdEntity.id!!,
            mapOf(
                Pair("description", "new description"),
                Pair("user", NonSecConfiguration.NON_SEC_USER_ID),
                Pair("status", TaskState.DONE)
            )
        ).get("$.data.updateTask", targetClass)

        assertThat(updatedEntity.description).isEqualTo("new description")
        assertThat(updatedEntity.status).isEqualTo(TaskState.DONE)
    }

    private fun createTask(userId: String = NonSecConfiguration.NON_SEC_USER_ID) = performGQLByInput("CreateTask", mapOf(Pair("user", userId), Pair("description", "test task"))).get("$.data.createTask", targetClass)
}
