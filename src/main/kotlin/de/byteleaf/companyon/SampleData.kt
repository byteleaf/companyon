package de.byteleaf.companyon

import de.byteleaf.companyon.company.control.CompanyService
import de.byteleaf.companyon.company.dto.input.CompanyInput
import de.byteleaf.companyon.fileupload.dto.input.FileMetaInput
import de.byteleaf.companyon.user.control.UserService
import de.byteleaf.companyon.user.dto.input.UserInput
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class SampleData : ApplicationRunner {

    @Autowired
    private lateinit var userService: UserService
    @Autowired
    private lateinit var companyService: CompanyService

    override fun run(args: ApplicationArguments) {
        val file = FileMetaInput("file-id", "https://reshape.sport1.de/c/t/B718E483-EDB8-4F18-86B9-0CDCADDB840E/640x400", "image/jpeg")
        userService.deleteAll()
        userService.create(UserInput("Jeff", "Bytezos", file, file))
        userService.create(UserInput("Crack", "Bytezos", file, file))
        userService.create(UserInput("Manuel", "Neuer", file, file))

        companyService.create(CompanyInput("Rainer Langer GmbH"))
        companyService.create(CompanyInput("Tali Schleif-irgendwas AG"))
    }
}