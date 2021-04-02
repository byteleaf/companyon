package de.byteleaf.companyon.test

import com.graphql.spring.boot.test.GraphQLResponse
import com.graphql.spring.boot.test.GraphQLTestSubscription
import de.byteleaf.companyon.test.mock.SecurityContextServiceMock
import de.byteleaf.companyon.test.util.GQLErrorUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Import
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

/**
 * The security context won't be set correctly during the tests, that is why [SecurityContextServiceMock] is needed.
 */
@Import(SecurityContextServiceMock::class)
abstract class AbstractSubscriptionIT(gqlFolder: String) : AbstractIT(gqlFolder) {

    companion object {
        const val SUBSCRIPTION_TIMEOUT = 1000
        const val SUBSCRIPTION_DELAY_BEFORE_TRIGGER_EVENT = 200L
    }

    @Suppress("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    protected lateinit var graphQLTestSubscription: GraphQLTestSubscription
    protected fun performGQLSubscription(gqlOperation: String, eventFunc: () -> Unit, payload: String? = null, skipValidation: Boolean = false): GraphQLResponse {
        graphQLTestSubscription.reset()
        val subscription = startGQLSubscription(gqlOperation, eventFunc, payload)
        val response = subscription.awaitAndGetNextResponse(SUBSCRIPTION_TIMEOUT, true)
        return if (skipValidation) response else GQLErrorUtil.validateResponse(response)
    }

    protected fun performGQLSubscriptionNoResponse(gqlOperation: String, eventFunc: () -> Unit, payload: String? = null) {
        val subscription = startGQLSubscription(gqlOperation, eventFunc, payload)
        subscription.waitAndExpectNoResponse(SUBSCRIPTION_TIMEOUT, true)
    }

    protected fun startGQLSubscription(gqlOperation: String, eventFunc: () -> Unit, payload: String? = null): GraphQLTestSubscription {
        graphQLTestSubscription.reset()
        val subscription = graphQLTestSubscription.start(getGQLResource(gqlOperation))
        // delay should not be to short (20 ms was tooo short !!!)
        Executors.newScheduledThreadPool(1).schedule(eventFunc, SUBSCRIPTION_DELAY_BEFORE_TRIGGER_EVENT, TimeUnit.MILLISECONDS)
        return subscription
    }
}
