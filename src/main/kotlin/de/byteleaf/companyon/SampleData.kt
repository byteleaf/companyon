package de.byteleaf.companyon

import de.byteleaf.companyon.company.control.CompanyService
import de.byteleaf.companyon.company.dto.input.CompanyInput
import de.byteleaf.companyon.fileupload.dto.input.FileMetaInput
import de.byteleaf.companyon.project.control.ProjectService
import de.byteleaf.companyon.project.dto.input.ProjectInput
import de.byteleaf.companyon.user.control.UserService
import de.byteleaf.companyon.user.dto.input.UserInput
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class SampleData : ApplicationRunner {

    companion object {
        const val FILE_ID = "file-id"
        const val MIME_TYPE = "image/jpeg"
    }

    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var companyService: CompanyService

    @Autowired
    private lateinit var projectService: ProjectService

    override fun run(args: ApplicationArguments) {

        val file1 = FileMetaInput(FILE_ID,
                "https://images.unsplash.com/photo-1494790108377-be9c29b29330?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=634&q=80", MIME_TYPE)

        val file2 = FileMetaInput(FILE_ID,
                "https://images.unsplash.com/photo-1530645298377-82c8416d3f90?ixlib=rb-1.2.1&auto=format&fit=crop&w=634&q=80", MIME_TYPE)

        val file3 = FileMetaInput(FILE_ID,
                "https://images.unsplash.com/photo-1519474072549-535cde8b7e7d?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=712&q=80", MIME_TYPE)

        val signatureFile = FileMetaInput(FILE_ID,
                "https://images.unsplash.com/photo-1589330694653-ded6df03f754?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1389&q=80", MIME_TYPE)

        userService.deleteAll()
        userService.create(UserInput("Jeff", "Bytezos", signatureFile, file1))
        userService.create(UserInput("Crack", "Bytezos", signatureFile, file2))
        userService.create(UserInput("Manuel", "Neuer", signatureFile, file3))


        companyService.deleteAll()
        companyService.create(CompanyInput("Company A Ltd."))
        companyService.create(CompanyInput("Company B Ltd."))

        projectService.deleteAll()
        projectService.create(ProjectInput("Project A"))
        projectService.create(ProjectInput("Project B"))
        
    }
}
