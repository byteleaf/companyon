package de.byteleaf.companyon.user.subscription

import de.byteleaf.companyon.auth.configuration.NonSecConfiguration
import de.byteleaf.companyon.common.dto.EntityUpdateType
import de.byteleaf.companyon.test.AbstractSubscriptionIT
import de.byteleaf.companyon.user.dto.UserUpdate
import de.byteleaf.companyon.user.dto.input.UserInput
import de.byteleaf.companyon.user.logic.UserService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class UserSubscriptionIT : AbstractSubscriptionIT("user") {

    @Autowired
    private lateinit var userService: UserService

    @BeforeEach
    fun setup() {
        userService.deleteAll(NonSecConfiguration.NON_SEC_USER_ID)
    }

    @Test
    fun getCurrentUser() {
        val entityUpdated = performGQLSubscription(
            "UserUpdateSubscription",
            { userService.update(NonSecConfiguration.NON_SEC_USER_ID, UserInput("a", "b", "c", false)) }
        )
            .get("$.data.userUpdate", UserUpdate::class.java)
        assertThat(entityUpdated.type).isEqualTo(EntityUpdateType.UPDATED)
        assertThat(entityUpdated.entity!!.lastName).isEqualTo("b")
    }

    @Test
    fun getDifferentUser() {
        val user = userService.create(UserInput("Hans", "Bytezos", "hans@byteleaf.de", false))
        performGQLSubscriptionNoResponse(
            "UserUpdateSubscription",
            { userService.update(user.id, UserInput("a", "b", "c", false)) }
        )
    }
}
