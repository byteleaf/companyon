package de.byteleaf.companyon.user.boundary

import com.graphql.spring.boot.test.GraphQLTest
import com.graphql.spring.boot.test.GraphQLTestTemplate
import de.byteleaf.companyon.common.AbstractGraphQLTest
import de.byteleaf.companyon.fileupload.dto.FileMeta
import de.byteleaf.companyon.user.entity.UserEntity
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito.doReturn
import org.springframework.beans.factory.annotation.Autowired

@GraphQLTest
class UserQueryResolverTest : AbstractGraphQLTest() {
    @Autowired
    protected lateinit var graphQLTestTemplate: GraphQLTestTemplate

    @Test
    fun testGetCurrentUser() {
        val signature = FileMeta("", "", "")
        val user = UserEntity("Joseph", "Bytezos", signature, null)
        user.id = "1234"

        doReturn(listOf(user)).`when`(userRepository).findAll()

        val response = graphQLTestTemplate.postForResource("graphql/testGetCurrentUser.graphql")

        Assertions.assertThat(response.isOk).isTrue()
        Assertions.assertThat(response.readTree().hasNonNull("errors"))
                .describedAs("response has errors")
                .isFalse()
        Assertions.assertThat(response.get("$.data.currentUser.id")).isNotNull()
        Assertions.assertThat(response.get("$.data.currentUser.firstName")).isEqualTo(user.firstName)
        Assertions.assertThat(response.get("$.data.currentUser.lastName")).isEqualTo(user.lastName)
    }
}