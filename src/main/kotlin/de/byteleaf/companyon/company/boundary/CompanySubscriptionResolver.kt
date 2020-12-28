package de.byteleaf.companyon.company.boundary

import graphql.kickstart.tools.GraphQLSubscriptionResolver
import org.springframework.stereotype.Component
//
//@Component
//class CompanySubscriptionResolver: GraphQLSubscriptionResolver {
//   // private var stockTickerPublisher: StockTickerRxPublisher? = null
//
////    fun Subscription(stockTickerPublisher: StockTickerRxPublisher?) {
////        this.stockTickerPublisher = stockTickerPublisher
////    }
////
////    fun companyUpdates(): Publisher<StockPriceUpdate?>? {
////        return stockTickerPublisher.getPublisher(stockCodes)
////    }
//}


//CompanyUpdated {
//    type: DELETED | UPDATED | CREATED,
//    entityId: "1234",
//    entity: Company(.....)  // if UPDATED or CREATED
//}