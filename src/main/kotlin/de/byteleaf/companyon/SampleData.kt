package de.byteleaf.companyon

import de.byteleaf.companyon.company.control.CompanyService
import de.byteleaf.companyon.company.dto.input.CompanyInput
import de.byteleaf.companyon.project.control.ProjectService
import de.byteleaf.companyon.project.dto.input.ProjectInput
import de.byteleaf.companyon.user.control.UserService
import de.byteleaf.companyon.user.dto.input.UserInput
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

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
        // will be used as current used if securtiy is disabled
        userService.create(UserInput("Jeff", "Bytezos", "jeff@byteleaf.de"), nonSecOAuth2Subject)
        userService.create(UserInput("Markus", "Heer", "markus.heer@byteleaf.de"))
        userService.create(UserInput("Simon", "Ittmann", "simon.ittmann@byteleaf.de"))
        userService.create(UserInput("Anthony", "Potdevin", "anthony.potdevin@byteleaf.de"))
        userService.create(UserInput("Paul", "Tolstoi", "paul.tolstoi@byteleaf.de"))
        userService.create(UserInput("Stefan", "Sauterleute", "stefan.sauterleute@byteleaf.de"))
    }
}
