package de.byteleaf.companyon.user

import de.byteleaf.companyon.AbstractIT
import de.byteleaf.companyon.user.control.UserService
import de.byteleaf.companyon.user.dto.input.UserInput
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

/**
 * All tests inside this class will be executed as user without ROLE_ADMIN!
 */
class UserInvalidAccessIT : AbstractIT("user") {

    @Autowired
    protected lateinit var userService: UserService

    @BeforeEach
    fun init() {
        userService.deleteAll()
    }

    @Test
    fun getUsers() {
        userService.create(UserInput("Hans", "Bytezos", "hans@byteleaf.de", false))
        expectAccessDenied(performGQL("GetUsers", null, true))
    }
}