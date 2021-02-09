package de.byteleaf.companyon.timelog

import de.byteleaf.companyon.auth.configuration.NonSecConfiguration
import de.byteleaf.companyon.auth.logic.SecurityContextService
import de.byteleaf.companyon.company.dto.input.CompanyInput
import de.byteleaf.companyon.company.logic.CompanyService
import de.byteleaf.companyon.project.dto.Project
import de.byteleaf.companyon.project.dto.ProjectInput
import de.byteleaf.companyon.project.dto.TimeLogGQLResponse
import de.byteleaf.companyon.project.logic.ProjectService
import de.byteleaf.companyon.test.AbstractIT
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
class TimeLogIT : AbstractIT("time-log") {

    private val targetClass = TimeLogGQLResponse::class.java

    @Autowired
    private lateinit var timeLogService: TimeLogService

    @Autowired
    private lateinit var projectService: ProjectService

    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var securityContextService: SecurityContextService

    @Autowired
    private lateinit var companyService: CompanyService

    // Mock data
    private lateinit var project1: Project
    private lateinit var user1: User

    @BeforeEach
    fun init() {
        userService.deleteAll()
        timeLogService.deleteAll()
        seedTestTimeLogs()
    }

    @Test
    fun create() {
        val u = userService.findAll()
        val createdEntity = performGQLByInput("CreateTimeLog", mapOf(Pair("start", "2011-12-03T10:15:30+01:00"),
            Pair("user", NonSecConfiguration.nonSecUserId),
            Pair("project", project1.id!!), Pair("description", "A"), Pair("durationInMinutes", 60), Pair("beakInMinutes", 15)))
            .get("$.data.createTimeLog", targetClass)
        Assertions.assertThat(createdEntity.description).isEqualTo("A")
//        Assertions.assertThat(createdEntity.project!!.name).isEqualTo("Project A")
//        Assertions.assertThat(createdEntity.user!!.firstName).isEqualTo("Joe")
        val u1 = userService.findAll()
        val a = 34
    }

    private fun seedTestTimeLogs() {
        project1 = projectService.create(ProjectInput("Project A",  companyService.create(CompanyInput("Company A")).id!!))
    }
}