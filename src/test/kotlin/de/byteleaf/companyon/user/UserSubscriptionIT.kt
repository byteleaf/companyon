package de.byteleaf.companyon.user

import de.byteleaf.companyon.common.dto.EntityUpdateType
import de.byteleaf.companyon.test.AbstractIT
import de.byteleaf.companyon.test.mock.SecurityContextServiceMock
import de.byteleaf.companyon.user.dto.User
import de.byteleaf.companyon.user.dto.UserUpdate
import de.byteleaf.companyon.user.dto.input.UserInput
import de.byteleaf.companyon.user.logic.UserService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Import
import org.springframework.context.annotation.PropertySource
import org.springframework.test.context.TestPropertySource

// TODO gql supscriptionIT
@TestPropertySource(properties = ["app.non-sec-user-admin=true"])
@Import(SecurityContextServiceMock::class)
class UserSubscriptionIT : AbstractIT("user") {

    @Autowired
    protected lateinit var userService: UserService

    @Test
    fun updatedSubscription() {
        val user = seedTestUser()
        val projectUpdated = performGQLSubscription("UserUpdateSubscription",
            { userService.update(user.id!!, UserInput("a", "b", "c", false)) })
            .get("$.data.userUpdate", UserUpdate::class.java)
        assertThat(projectUpdated.type).isEqualTo(EntityUpdateType.UPDATED)
        assertThat(projectUpdated.entity!!.lastName).isEqualTo("b")
    }

    private fun seedTestUser(): User =
        userService.create(UserInput("Hans", "Bytezos", "hans@byteleaf.de", false))
}