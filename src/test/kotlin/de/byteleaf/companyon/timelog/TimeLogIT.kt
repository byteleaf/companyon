package de.byteleaf.companyon.timelog

import de.byteleaf.companyon.company.dto.input.CompanyInput
import de.byteleaf.companyon.company.logic.CompanyService
import de.byteleaf.companyon.project.dto.Project
import de.byteleaf.companyon.project.dto.ProjectInput
import de.byteleaf.companyon.project.logic.ProjectService
import de.byteleaf.companyon.test.AbstractIT
import de.byteleaf.companyon.timelog.dto.TimeLog
import de.byteleaf.companyon.timelog.logic.TimeLogService
import de.byteleaf.companyon.user.dto.User
import de.byteleaf.companyon.user.dto.input.UserInput
import de.byteleaf.companyon.user.logic.UserService
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired

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
    private lateinit var user1: User

    @BeforeEach
    fun init() {
        timeLogService.deleteAll()
        seedTestTimeLogs()
    }

//    @Test
//    fun create() {
//        seedTestTimeLogs()
//        val createdProject = performGQLByInput("CreateTimeLog", mapOf(Pair("from", "2011-12-03T10:15:30+01:00")))
//            .get("$.data.createTimeLog", targetClass)
//        Assertions.assertThat(createdProject.name).isEqualTo("A")
//        Assertions.assertThat(createdProject.state).isEqualTo(ProjectState.PLANNED)
//        // Check if really existing
//        val getResponse = performGQLById("GetProject", createdProject.id).get("$.data.project", targetClass)
//        Assertions.assertThat(getResponse.name).isEqualTo("A")
//    }

    private fun seedTestTimeLogs() {
        user1 = userService.create(UserInput("Joe", "Byten", "joe@byteleaf.de"))
        project1 = projectService.create(ProjectInput("Project A",  companyService.create(CompanyInput("Company A")).id!!))
    }
}