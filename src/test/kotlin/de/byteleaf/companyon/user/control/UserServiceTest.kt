package de.byteleaf.companyon.user.control

import de.byteleaf.companyon.CompanyonTextContextConfiguration
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension


@DataMongoTest
@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [CompanyonTextContextConfiguration::class])
class UserServiceTest {

    @Autowired
    private lateinit var userService: UserService

    @Test
    fun getCurrentUser() {
        val currentUser = userService.getCurrentUser()

        assertEquals("Jeff", currentUser.firstName)
        assertEquals("Bytezos", currentUser.lastName)
    }
}