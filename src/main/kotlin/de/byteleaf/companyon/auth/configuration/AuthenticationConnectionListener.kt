package de.byteleaf.companyon.auth.control

import graphql.kickstart.execution.subscriptions.SubscriptionSession
import graphql.kickstart.execution.subscriptions.apollo.ApolloSubscriptionConnectionListener
import graphql.kickstart.execution.subscriptions.apollo.OperationMessage
import org.springframework.stereotype.Component

@Component
class AuthenticationConnectionListener : ApolloSubscriptionConnectionListener {

    override fun onConnect(session: SubscriptionSession?, message: OperationMessage?) {

        val i = 9
    }

//    fun onConnect(SubscriptionSession session, OperationMessage message) {
//        log.debug("onConnect with payload {}", message.getPayload().getClass());
//        String token = ((Map<String, String>) message.getPayload()).get("authToken");
//        log.info("Token: {}", token);
//        Authentication authentication = new UsernamePasswordAuthenticationToken(token, null);
//        session.getUserProperties().put("CONNECT_TOKEN", authentication);
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//    }

}