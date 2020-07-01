package de.byteleaf.companyon

import de.byteleaf.companyon.fileupload.dto.File
import de.byteleaf.companyon.fileupload.dto.input.FileInput
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

    override fun run(args: ApplicationArguments) {
        val file = FileInput("file-id", "https://reshape.sport1.de/c/t/B718E483-EDB8-4F18-86B9-0CDCADDB840E/640x400", "image/jpeg")
        userService.deleteAll()
        userService.create(UserInput("Jeff", "Bytezos", file, file))
        userService.create(UserInput("Crack", "Bytezos", file, file))
        userService.create(UserInput("Manuel", "Neuer", file, file))
    }
}