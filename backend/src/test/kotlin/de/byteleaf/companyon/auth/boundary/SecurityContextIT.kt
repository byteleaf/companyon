package de.byteleaf.companyon.auth.boundary

import de.byteleaf.companyon.test.AbstractQueryMutationIT
import de.byteleaf.companyon.user.dto.UserGQLResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SecurityContextIT : AbstractQueryMutationIT("auth") {

    @Test
    fun getCurrentUser() {
        val response = performGQL("GetCurrentUser").get("$.data.currentUser", UserGQLResponse::class.java)
        assertThat(response.email).isEqualTo("jeff@byteleaf.de")
    }
}
