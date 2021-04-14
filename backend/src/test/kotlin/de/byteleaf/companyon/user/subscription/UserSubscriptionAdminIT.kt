package de.byteleaf.companyon.user.subscription

import de.byteleaf.companyon.common.dto.EntityUpdateType
import de.byteleaf.companyon.test.AbstractSubscriptionIT
import de.byteleaf.companyon.user.dto.UserUpdateGQLResponse
import de.byteleaf.companyon.user.dto.input.UserInput
import de.byteleaf.companyon.user.logic.UserService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.TestPropertySource

@TestPropertySource(properties = ["app.non-sec-user-admin=true"])
class UserSubscriptionAdminIT : AbstractSubscriptionIT("user") {

    @Autowired
    private lateinit var userService: UserService

    @Test
    fun getDifferentUser() {
        val user = userService.create(UserInput("Hans", "Bytezos", "hans@byteleaf.de", false))
        val projectUpdated = performGQLSubscription(
            "UserUpdateSubscription",
            { userService.update(user.id, UserInput("a", "b", "c", false)) }
        ).get("$.data.userUpdate", UserUpdateGQLResponse::class.java)
        assertThat(projectUpdated.type).isEqualTo(EntityUpdateType.UPDATED)
        assertThat(projectUpdated.entity.lastName).isEqualTo("b")
    }
}
