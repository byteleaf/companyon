package de.byteleaf.companyon.test

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.ObjectNode
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ActiveProfiles

// The order is important -> test is overwriting some properties
@ActiveProfiles(profiles = ["non-sec", "test"])
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureDataMongo
// needed, otherwise embedded mongo db will produce a "Could not start process: <EOF>" after executing multiple tests in a row
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
abstract class AbstractIT(val gqlFolder: String) {

    @Autowired
    protected lateinit var objectMapper: ObjectMapper

    /**
     * If the [gqlOperation] contains no slash the default folder will be appended
     */
    protected fun getGQLResource(gqlOperation: String): String {
        if (!gqlOperation.contains("/")) {
            return "graphql/$gqlFolder/$gqlOperation.graphql"
        }
        return "graphql/$gqlOperation.graphql"
    }

    protected fun parseJSON(payload: String? = null): ObjectNode? {
        if (payload != null) return objectMapper.readTree(payload) as ObjectNode?
        return null
    }
}