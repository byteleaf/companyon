package de.byteleaf.companyon.company.boundary

import de.byteleaf.companyon.company.control.CompanyService
import de.byteleaf.companyon.company.dto.CompanyUpdated
import graphql.kickstart.tools.GraphQLSubscriptionResolver
import org.reactivestreams.Publisher
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller

@Controller
class CompanySubscriptionResolver : GraphQLSubscriptionResolver {
    @Autowired
    private lateinit var companyService: CompanyService

    fun companyUpdated(): Publisher<CompanyUpdated> = companyService.getPublisher()
}