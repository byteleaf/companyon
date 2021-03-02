package de.byteleaf.companyon.timelog

import de.byteleaf.companyon.auth.configuration.NonSecConfiguration
import de.byteleaf.companyon.company.dto.input.CompanyInput
import de.byteleaf.companyon.company.logic.CompanyService
import de.byteleaf.companyon.project.dto.Project
import de.byteleaf.companyon.project.dto.ProjectInput
import de.byteleaf.companyon.project.dto.TimeLogGQLResponse
import de.byteleaf.companyon.project.logic.ProjectService
import de.byteleaf.companyon.test.AbstractIT
import de.byteleaf.companyon.timelog.logic.TimeLogService
import de.byteleaf.companyon.user.logic.UserService
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class TimeLogIT : AbstractIT("time-log") {

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

    @BeforeEach
    fun init() {
        userService.deleteAll(NonSecConfiguration.NON_SEC_USER_ID)
        timeLogService.deleteAll()
        seedTestTimeLogs()
    }

    @Test
    fun create() {
        val createdEntity = createTimeLog()
        Assertions.assertThat(createdEntity.description).isEqualTo("A")
        Assertions.assertThat(createdEntity.project!!.name).isEqualTo("Project A")
        Assertions.assertThat(createdEntity.user!!.firstName).isEqualTo("Jeff")
    }

    @Test
    fun delete() {
        val createdEntity = createTimeLog()
        Assertions.assertThat(timeLogService.findAll().size).isEqualTo(1)
        performGQLById("DeleteTimeLog", createdEntity.id!!)
        Assertions.assertThat(timeLogService.findAll().size).isEqualTo(0)
    }

    @Test
    fun update() {
        val createdEntity = createTimeLog()
        val updatedEntity = performGQLByIdAndInput("UpdateTimeLog", createdEntity.id!!,
            mapOf(Pair("start", "2011-12-03T10:15:30+01:00"),
                Pair("user", NonSecConfiguration.NON_SEC_USER_ID),
                Pair("project", project1.id!!), Pair("description", "A"), Pair("durationInMinutes", 37)))
            .get("$.data.updateTimeLog", targetClass)
        Assertions.assertThat(updatedEntity.durationInMinutes).isEqualTo(37)
    }

    private fun createTimeLog(userId: String = NonSecConfiguration.NON_SEC_USER_ID) = performGQLByInput("CreateTimeLog", mapOf(Pair("start", "2011-12-03T10:15:30+01:00"),
        Pair("user", userId),
        Pair("project", project1.id!!), Pair("description", "A"), Pair("durationInMinutes", 60), Pair("breakInMinutes", 15)))
        .get("$.data.createTimeLog", targetClass)

    private fun seedTestTimeLogs() {
        project1 = projectService.create(ProjectInput("Project A",  companyService.create(CompanyInput("Company A")).id!!))
    }
}