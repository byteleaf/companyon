package de.byteleaf.companyon.timelog

import de.byteleaf.companyon.company.dto.input.CompanyInput
import de.byteleaf.companyon.company.logic.CompanyService
import de.byteleaf.companyon.project.dto.Project
import de.byteleaf.companyon.project.dto.ProjectInput
import de.byteleaf.companyon.project.dto.TimeLogGQLResponse
import de.byteleaf.companyon.project.logic.ProjectService
import de.byteleaf.companyon.test.AbstractQueryMutationIT
import de.byteleaf.companyon.test.util.DateTimeUtil
import de.byteleaf.companyon.timelog.dto.TimeLogInput
import de.byteleaf.companyon.timelog.logic.TimeLogService
import de.byteleaf.companyon.user.dto.User
import de.byteleaf.companyon.user.dto.input.UserInput
import de.byteleaf.companyon.user.logic.UserService
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.TestPropertySource

@TestPropertySource(properties = ["app.non-sec-user-admin=true"])
class GetTimeLogsIT : AbstractQueryMutationIT("time-log") {

    private val targetClass = TimeLogGQLResponse::class.java

    @Autowired
    private lateinit var timeLogService: TimeLogService

    @Autowired
    private lateinit var projectService: ProjectService

    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var companyService: CompanyService

    // Mock data
    private lateinit var project1: Project
    private lateinit var project2: Project
    private lateinit var user1: User
    private lateinit var user2: User

    @BeforeEach
    fun init() {
        userService.deleteAll()
        projectService.deleteAll()
        companyService.deleteAll()
        timeLogService.deleteAll()
        seedTestTimeLogs()
    }

    @Test
    fun getTimeLogsUserAndProject() {
        assertTimeLogs(mapOf(), 3)
        assertTimeLogs(mapOf(Pair("projectId", project2.id)), 2)
        assertTimeLogs(mapOf(Pair("userId", user2.id)), 2)
        assertTimeLogs(mapOf(Pair("projectId", project2.id), Pair("userId", user2.id)), 1)
    }

    @Test
    fun getTimeLogsFrom() {
        assertTimeLogs(mapOf(Pair("from", "2011-12-03T10:15:30+01:00")), 3)
        assertTimeLogs(mapOf(Pair("from", "2011-12-03T10:15:31+01:00")), 2)
    }

    @Test
    fun getTimeLogsTo() {
        assertTimeLogs(mapOf(Pair("to", "2011-12-03T10:15:30+01:00")), 1)
        assertTimeLogs(mapOf(Pair("to", "2011-12-05T10:17:29+01:00")), 1)
        assertTimeLogs(mapOf(Pair("to", "2011-12-05T10:18:30+01:00")), 3)
    }

    @Test
    fun getTimeLogsFromTo() {
        assertTimeLogs(mapOf(Pair("from", "2011-12-03T10:15:31+01:00"), Pair("to", "2011-12-05T10:18:29+01:00")), 1)
        assertTimeLogs(mapOf(Pair("from", "2011-12-03T10:15:30+01:00"), Pair("to", "2011-12-05T10:18:30+01:00")), 3)
    }

    @Test
    fun getTimeLogs() {
        assertTimeLogs(mapOf(Pair("from", "2011-12-03T10:15:31+01:00"), Pair("to", "2011-12-05T10:18:30+01:00"), Pair("userId", user2.id)), 1)
    }

    private fun assertTimeLogs(parameter: Map<String, String>, expectedLogs: Int) =
        Assertions.assertThat(performGQL("GetTimeLogs", parameter).getList("$.data.timeLogs", targetClass).size).isEqualTo(expectedLogs)

    private fun seedTestTimeLogs() {
        user1 = userService.create(UserInput("Joe", "Byten", "joe@byteleaf.de"))
        user2 = userService.create(UserInput("Jeff", "Bytezos", "jeff@byteleaf.de"))
        val companyId = companyService.create(CompanyInput("Company A")).id
        project1 = projectService.create(ProjectInput("Project A", companyId))
        project2 = projectService.create(ProjectInput("Project B", companyId))
        timeLogService.create(TimeLogInput(user2.id, project2.id, DateTimeUtil.getDateTime("2011-12-03T10:15:30+01:00"), 60, "a"))
        timeLogService.create(TimeLogInput(user2.id, project1.id, DateTimeUtil.getDateTime("2011-12-05T10:17:30+01:00"), 60, "b"))
        timeLogService.create(TimeLogInput(user1.id, project2.id, DateTimeUtil.getDateTime("2011-12-05T10:18:30+01:00"), 60, "c"))
    }
}
