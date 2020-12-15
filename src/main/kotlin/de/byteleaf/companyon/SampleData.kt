package de.byteleaf.companyon

import de.byteleaf.companyon.company.control.CompanyService
import de.byteleaf.companyon.company.dto.input.CompanyInput
import de.byteleaf.companyon.project.control.ProjectService
import de.byteleaf.companyon.project.dto.input.ProjectInput
import de.byteleaf.companyon.user.control.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
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

    override fun run(args: ApplicationArguments) {
        if (skipSampleData) {
            return
        }

        userService.deleteAll()


        companyService.deleteAll()
        companyService.create(CompanyInput("Company A Ltd."))
        companyService.create(CompanyInput("Company B Ltd."))

        projectService.deleteAll()
        projectService.create(ProjectInput("Project A"))
        projectService.create(ProjectInput("Project B"))

    }
}
