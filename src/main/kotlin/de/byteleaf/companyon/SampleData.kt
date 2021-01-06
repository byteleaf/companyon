package de.byteleaf.companyon

import de.byteleaf.companyon.common.entity.EntityType
import de.byteleaf.companyon.common.event.EntityDeletedEvent
import de.byteleaf.companyon.company.control.CompanyService
import de.byteleaf.companyon.company.dto.input.CompanyInput
import de.byteleaf.companyon.project.control.ProjectService
import de.byteleaf.companyon.project.dto.input.ProjectInput
import de.byteleaf.companyon.user.control.SecurityService
import de.byteleaf.companyon.user.control.UserService
import de.byteleaf.companyon.user.dto.input.UserInput
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component
import java.util.*

@Component
class SampleData : ApplicationRunner {

    @Value("\${skip-sample-data:false}")
    private var skipSampleData = false

    @Value("\${app.non-sec-oauth2-subject}")
    private lateinit var nonSecOAuth2Subject: String

    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var companyService: CompanyService

    @Autowired
    private lateinit var projectService: ProjectService

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
        projectService.create(ProjectInput("Project A", companyA.id!!))
        projectService.create(ProjectInput("Project B", companyA.id!!))
        projectService.create(ProjectInput("Project C", companyB.id!!))
        projectService.create(ProjectInput("Project D", companyB.id!!))

        // Users
        userService.deleteAll()
        userService.create(UserInput("Jeff", "Bytezos", "jeff@byteleaf.de"), nonSecOAuth2Subject)

    }
}
