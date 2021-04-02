package de.byteleaf.companyon.task.subscription

import de.byteleaf.companyon.auth.configuration.NonSecConfiguration
import de.byteleaf.companyon.task.dto.Task
import de.byteleaf.companyon.task.dto.TaskInput
import de.byteleaf.companyon.task.logic.TaskService
import de.byteleaf.companyon.test.AbstractSubscriptionIT
import de.byteleaf.companyon.timelog.logic.TimeLogService
import de.byteleaf.companyon.user.logic.UserService
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired

class AbstractTaskSubscription : AbstractSubscriptionIT("task") {

    @Autowired
    protected lateinit var timeLogService: TimeLogService

    @Autowired
    protected lateinit var taskService: TaskService

    @Autowired
    protected lateinit var userService: UserService

    @BeforeEach
    fun setup() {
        userService.deleteAll(NonSecConfiguration.NON_SEC_USER_ID)
        taskService.deleteAll()
    }

    protected fun createTask(userId: String = NonSecConfiguration.NON_SEC_USER_ID): Task {
        return taskService.create(
            TaskInput(userId, "test task")
        )
    }
}
