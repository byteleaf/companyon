package de.byteleaf.companyon.user.control

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import de.byteleaf.companyon.user.dto.User

@SpringBootTest
class UserServiceTest {

    @Test
    fun getCurrentUser() {
        // just returns a value without any recursive calls
        assertEquals(User("99", "Jeff", "Bytezos"), 2)
    }
}