package de.byteleaf.companyon

import de.byteleaf.companyon.common.event.EntityCreatedEvent
import de.byteleaf.companyon.common.event.EntityDeletedEvent
import de.byteleaf.companyon.common.event.EventEntityType
import de.byteleaf.companyon.company.control.CompanyService
import de.byteleaf.companyon.company.dto.input.CompanyInput
import de.byteleaf.companyon.company.entity.CompanyEntity
import de.byteleaf.companyon.project.control.ProjectService
import de.byteleaf.companyon.project.dto.input.ProjectInput
import de.byteleaf.companyon.user.control.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component

@Component
class SampleData : ApplicationRunner {

    companion object {
        const val FILE_ID = "file-id"
        const val MIME_TYPE = "image/jpeg"
    }

    @Value("\${skip-sample-data:false}")
    private var skipSampleData = false

    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var companyService: CompanyService

    @Autowired
    private lateinit var projectService: ProjectService
    @Autowired
    protected lateinit var applicationEventPublisher: ApplicationEventPublisher
    override fun run(args: ApplicationArguments) {
        if (skipSampleData) {
            return
        }

        userService.deleteAll()

        companyService.deleteAll()

        val companyA = companyService.create(CompanyInput("Company A Ltd."))
        val companyB = companyService.create(CompanyInput("Company B Ltd."))

        projectService.deleteAll()
        projectService.create(ProjectInput("Project A", companyA.id!!))
        projectService.create(ProjectInput("Project B", companyA.id!!))

        projectService.create(ProjectInput("Project C", companyB.id!!))
        projectService.create(ProjectInput("Project D", companyB.id!!))

        applicationEventPublisher.publishEvent(EntityDeletedEvent<CompanyEntity>(EventEntityType.COMPANY, "4", CompanyEntity("32")))
    }
}
