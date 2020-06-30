package de.byteleaf.companyon

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
        userService.create(UserInput("Jeff", "Bytezos", "https://profile-images.xing.com/images/e9347adcf390f13f4809c7b08653a73b-2/simon-ittmann.1024x1024.jpg"))
        userService.create(UserInput("Crack", "Bytezos", "https://media.graphcms.com/resize=width:100/25QFLjz6RZOUejx8DBD6"))
        userService.create(UserInput("Manuel", "Neuer", "https://images.sportbuzzer.de/v1/photos/raw/munich-germany-january-25-manuel-neuer-of-fc-bayern-muenchen-runs-during-the-bun/large-16-9"))
    }
}