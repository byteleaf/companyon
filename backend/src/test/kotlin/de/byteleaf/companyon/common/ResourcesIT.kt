package de.byteleaf.companyon.common

import de.byteleaf.companyon.test.AbstractIT
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus

@AutoConfigureWebClient(registerRestTemplate = true)
class ResourcesIT : AbstractIT("user") {

    @Autowired
    protected lateinit var testRestTemplate: TestRestTemplate

    @Test
    fun testFallbackIndex() {
        val response = testRestTemplate.exchange("/", HttpMethod.GET, null, String::class.java)

        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(response.body).contains("Fallback File")
    }

    @Test
    fun testFallbackSubroute() {
        val response = testRestTemplate.exchange("/dashboard", HttpMethod.GET, null, String::class.java)

        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(response.body).contains("Fallback File")
    }

    @Test
    fun testExistingFileInSubfolder() {
        val response = testRestTemplate.exchange("/subfolder/file.txt", HttpMethod.GET, null, String::class.java)

        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(response.body).contains("file in a subfolder")
    }

    @Test
    fun testGraphQLEndpoint() {
        val request = HttpEntity("""{ "query": "query test { __typename }" }""")

        val response = testRestTemplate.exchange("/graphql", HttpMethod.POST, request, String::class.java)

        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(response.body).contains("""{"data":{"__typename":"Query"}}""")
    }

    @Test
    fun testGraphQLPlayground() {
        val response = testRestTemplate.exchange("/playground", HttpMethod.GET, null, String::class.java)

        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(response.body).contains("GraphQL Playground")
    }
}