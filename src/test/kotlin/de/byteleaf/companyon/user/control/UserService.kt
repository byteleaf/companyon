package de.byteleaf.companyon.user.control

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UserServiceTest {

    @Autowired
    private lateinit var userService: UserService;

    @Test
    fun getCurrentUser() {
        val currentUser = userService.getCurrentUser()
        assertEquals("Jeff", currentUser.firstName)
        assertEquals("Bytezos", currentUser.lastName)
    }
}