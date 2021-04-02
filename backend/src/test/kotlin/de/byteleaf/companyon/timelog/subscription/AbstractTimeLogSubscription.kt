package de.byteleaf.companyon.timelog.subscription

import de.byteleaf.companyon.auth.configuration.NonSecConfiguration
import de.byteleaf.companyon.test.AbstractSubscriptionIT
import de.byteleaf.companyon.timelog.dto.TimeLog
import de.byteleaf.companyon.timelog.dto.TimeLogInput
import de.byteleaf.companyon.timelog.logic.TimeLogService
import de.byteleaf.companyon.user.logic.UserService
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import java.time.OffsetDateTime

class AbstractTimeLogSubscription : AbstractSubscriptionIT("time-log") {

    @Autowired
    protected lateinit var timeLogService: TimeLogService

    @Autowired
    protected lateinit var userService: UserService

    @BeforeEach
    fun setup() {
        userService.deleteAll(NonSecConfiguration.NON_SEC_USER_ID)
        timeLogService.deleteAll()
    }

    protected fun createTimeLog(userId: String = NonSecConfiguration.NON_SEC_USER_ID): TimeLog {
        return timeLogService.create(
            TimeLogInput(
                userId, "project1", OffsetDateTime.parse("2011-12-03T10:15:30+01:00"),
                60, "description", 15
            )
        )
    }
}
