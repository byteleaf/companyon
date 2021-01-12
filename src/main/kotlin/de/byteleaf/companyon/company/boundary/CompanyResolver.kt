package de.byteleaf.companyon.company.boundary

import de.byteleaf.companyon.company.control.CompanyService
import de.byteleaf.companyon.company.dto.Company
import de.byteleaf.companyon.company.dto.CompanyUpdate
import de.byteleaf.companyon.company.dto.input.CompanyInput
import graphql.kickstart.tools.GraphQLMutationResolver
import graphql.kickstart.tools.GraphQLQueryResolver
import graphql.kickstart.tools.GraphQLSubscriptionResolver
import org.reactivestreams.Publisher
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Controller

@Controller
class CompanyResolver : GraphQLMutationResolver, GraphQLQueryResolver, GraphQLSubscriptionResolver {

    @Autowired
    private lateinit var companyService: CompanyService

    fun getCompany(id: String): Company = companyService.get(id)

    fun getCompanies(): List<Company>  = companyService.findAll()

    fun createCompany(input: CompanyInput): Company = companyService.create(input)

    fun updateCompany(id: String, input: CompanyInput): Company = companyService.update(id, input)

    fun deleteCompany(id: String): Company = companyService.delete(id)

    fun companyUpdate(): Publisher<CompanyUpdate> = companyService.getPublisher()
}