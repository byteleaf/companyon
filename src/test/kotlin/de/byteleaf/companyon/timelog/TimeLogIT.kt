package de.byteleaf.companyon.timelog

import de.byteleaf.companyon.company.dto.input.CompanyInput
import de.byteleaf.companyon.company.logic.CompanyService
import de.byteleaf.companyon.project.dto.Project
import de.byteleaf.companyon.project.dto.ProjectInput
import de.byteleaf.companyon.project.logic.ProjectService
import de.byteleaf.companyon.test.AbstractIT
import de.byteleaf.companyon.timelog.dto.TimeLog
import de.byteleaf.companyon.timelog.dto.TimeLogInput
import de.byteleaf.companyon.timelog.logic.TimeLogService
import de.byteleaf.companyon.user.dto.User
import de.byteleaf.companyon.user.dto.input.UserInput
import de.byteleaf.companyon.user.logic.UserService
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

class TimeLogIT : AbstractIT("time-log") {

    private val targetClass = TimeLog::class.java

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
        timeLogService.deleteAll()
    }

    @Test
    fun getTimeLogsByProject() {
        val logs = seedTestTimeLogs()
        val timeLogs = performGQL("GetTimeLogs", mapOf(Pair("projectId", project1.id!!))).getList("$.data.timeLogs", targetClass)
        Assertions.assertThat(timeLogs.size).isEqualTo(2)
    }

    private fun seedTestTimeLogs(): List<TimeLog> {
        user1 = userService.create(UserInput("Joe", "Byten", "joe@byteleaf.de"))
        user2 = userService.create(UserInput("Jeff", "Bytezos", "jeff@byteleaf.de"))
        val companyId = companyService.create(CompanyInput("Company A")).id!!
        project1 = projectService.create(ProjectInput("Project A", companyId))
        project2 = projectService.create(ProjectInput("Project B", companyId))
        return listOf(timeLogService.create(TimeLogInput(user2.id!!, project2.id!!, getDateTime("2011-12-03T10:15:30+01:00"), 60, "a")),
            timeLogService.create(TimeLogInput(user2.id!!, project1.id!!, getDateTime("2011-12-05T10:17:30+01:00"), 60, "b")),
            timeLogService.create(TimeLogInput(user1.id!!, project2.id!!, getDateTime("2011-12-05T10:18:30+01:00"), 60, "c")))
    }

    private fun getDateTime(dt: String) : OffsetDateTime = OffsetDateTime.parse(dt,  DateTimeFormatter.ISO_OFFSET_DATE_TIME)
}