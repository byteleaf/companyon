package de.byteleaf.companyon.user.control

import de.byteleaf.companyon.CompanyonTextContextConfiguration
import de.byteleaf.companyon.fileupload.dto.input.FileMetaInput
import de.byteleaf.companyon.user.dto.input.UserInput
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration

@DataMongoTest
@ContextConfiguration(classes = [CompanyonTextContextConfiguration::class])
@ActiveProfiles("test")
class UserServiceTest {

    @Autowired
    private lateinit var userService: UserService

    @Test
    fun getCurrentUser() {
        val emptyFileMeta = FileMetaInput("", "", "")
        val newUser = UserInput("Joseph", "Bytezos", emptyFileMeta, emptyFileMeta)

        userService.create(newUser)

        val currentUser = userService.getCurrentUser()

        assertEquals(newUser.firstName, currentUser.firstName)
        assertEquals(newUser.lastName, currentUser.lastName)
    }
}