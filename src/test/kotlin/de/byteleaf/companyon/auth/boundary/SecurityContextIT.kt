package de.byteleaf.companyon.auth.boundary

import de.byteleaf.companyon.AbstractIT
import de.byteleaf.companyon.user.dto.User
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test


class SecurityContextIT : AbstractIT("auth") {

    @Test
    fun getCurrentUser() {
        val response = performGQL("GetCurrentUser").get("$.data.currentUser", User::class.java)
        Assertions.assertThat(response.email).isEqualTo("jeff@byteleaf.de")
    }
}