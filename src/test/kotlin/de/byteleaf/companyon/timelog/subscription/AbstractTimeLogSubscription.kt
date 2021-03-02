package de.byteleaf.companyon.timelog.subscription

import de.byteleaf.companyon.auth.configuration.NonSecConfiguration
import de.byteleaf.companyon.project.dto.TimeLogGQLResponse
import de.byteleaf.companyon.test.AbstractIT
import de.byteleaf.companyon.timelog.logic.TimeLogService
import de.byteleaf.companyon.user.logic.UserService
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired

class AbstractTimeLogSubscription : AbstractIT("time-log") {

    @Autowired
    private lateinit var timeLogService: TimeLogService

    @Autowired
    private lateinit var userService: UserService

    @BeforeEach
    fun init() {
        userService.deleteAll(NonSecConfiguration.NON_SEC_USER_ID)
        timeLogService.deleteAll()
    }

    protected fun createTimeLog(userId: String = NonSecConfiguration.NON_SEC_USER_ID) = performGQLByInput("CreateTimeLog", mapOf(Pair("start", "2011-12-03T10:15:30+01:00"),
        Pair("user", userId),
        Pair("project", "55645"), Pair("description", "A"), Pair("durationInMinutes", 60), Pair("breakInMinutes", 15)))
        .get("$.data.createTimeLog", TimeLogGQLResponse::class.java)
}