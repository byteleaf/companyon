package de.byteleaf.companyon.user

import de.byteleaf.companyon.auth.configuration.NonSecConfiguration
import de.byteleaf.companyon.common.dto.EntityUpdateType
import de.byteleaf.companyon.test.AbstractIT
import de.byteleaf.companyon.test.mock.SecurityContextServiceMock
import de.byteleaf.companyon.user.dto.UserUpdate
import de.byteleaf.companyon.user.dto.input.UserInput
import de.byteleaf.companyon.user.logic.UserService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Import
import org.springframework.test.context.TestPropertySource

@Import(SecurityContextServiceMock::class)
class UserSubscriptionIT : AbstractIT("user") {

    @Autowired
    private lateinit var userService: UserService
    @Autowired
    private lateinit var nonSecConfiguration: NonSecConfiguration

    @BeforeEach
    fun setup() {
        userService.deleteAll()
    }

    @Test
    fun getCurrentUser() {
        nonSecConfiguration.createAndPersistNonSecUser()
        val projectUpdated = performGQLSubscription("UserUpdateSubscription",
            { userService.update(NonSecConfiguration.NON_SEC_USER_ID, UserInput("a", "b", "c", false)) })
            .get("$.data.userUpdate", UserUpdate::class.java)
        assertThat(projectUpdated.type).isEqualTo(EntityUpdateType.UPDATED)
        assertThat(projectUpdated.entity!!.lastName).isEqualTo("b")
    }

    @Test
    fun getDifferentUser() {
        val user = userService.create(UserInput("Hans", "Bytezos", "hans@byteleaf.de", false))
        performGQLSubscriptionNoResponse("UserUpdateSubscription",
            { userService.update(user.id!!, UserInput("a", "b", "c", false)) })
    }
}

@TestPropertySource(properties = ["app.non-sec-user-admin=true"])
@Import(SecurityContextServiceMock::class)
class UserSubscriptionAdminIT : AbstractIT("user") {

    @Autowired
    private lateinit var userService: UserService

    @Test
    fun getDifferentUser() {
        val user = userService.create(UserInput("Hans", "Bytezos", "hans@byteleaf.de", false))
        val projectUpdated = performGQLSubscription("UserUpdateSubscription",
            { userService.update(user.id!!, UserInput("a", "b", "c", false)) })
            .get("$.data.userUpdate", UserUpdate::class.java)
        assertThat(projectUpdated.type).isEqualTo(EntityUpdateType.UPDATED)
        assertThat(projectUpdated.entity!!.lastName).isEqualTo("b")
    }
}