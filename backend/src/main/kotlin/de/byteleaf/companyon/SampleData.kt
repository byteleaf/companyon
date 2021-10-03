package de.byteleaf.companyon

import de.byteleaf.companyon.company.dto.input.CompanyInput
import de.byteleaf.companyon.company.logic.CompanyService
import de.byteleaf.companyon.project.dto.ProjectInput
import de.byteleaf.companyon.project.logic.ProjectService
import de.byteleaf.companyon.timelog.dto.TimeLogInput
import de.byteleaf.companyon.timelog.logic.TimeLogService
import de.byteleaf.companyon.user.dto.input.UserInput
import de.byteleaf.companyon.user.logic.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

@Component
class SampleData : ApplicationRunner {

    @Value("\${skip-sample-data:false}")
    private var skipSampleData = false

    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var companyService: CompanyService

    @Autowired
    private lateinit var projectService: ProjectService

    @Autowired
    private lateinit var timeLogService: TimeLogService

    override fun run(args: ApplicationArguments) {
        if (skipSampleData) {
            return
        }

        // Companies
        companyService.deleteAll()
        val companyA = companyService.create(CompanyInput("Company A Ltd."))
        val companyB = companyService.create(CompanyInput("Company B Ltd."))

        // Projects
        projectService.deleteAll()
        val projectA = projectService.create(ProjectInput("Project A", companyA.id))
        projectService.create(ProjectInput("Project B", companyA.id))
        projectService.create(ProjectInput("Project C", companyB.id))
        projectService.create(ProjectInput("Project D", companyB.id))

        // Users
        userService.deleteAll()
        val markus = userService.create(UserInput("Markus", "Heer", "markus.heer@byteleaf.de", true))
        userService.create(UserInput("Simon", "Ittmann", "simon.ittmann@byteleaf.de", true))
        userService.create(UserInput("Paul", "Tolstoi", "paul.tolstoi@byteleaf.de", true))
        userService.create(UserInput("Ulrich", "Horn", "ulrich.horn@byteleaf.de", true))

        // TimeLogs
        timeLogService.deleteAll()
        timeLogService.create(TimeLogInput(markus.id, projectA.id, OffsetDateTime.parse("2021-08-03T10:00:00+02:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME), 480, "Learning react", 30))
        timeLogService.create(TimeLogInput(markus.id, projectA.id, OffsetDateTime.parse("2021-08-04T09:15:00+02:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME), 120, "Learning angular"))
    }
}
