package de.byteleaf.companyon.company.boundary

import de.byteleaf.companyon.company.access.CompanyAccessService
import de.byteleaf.companyon.company.dto.Company
import de.byteleaf.companyon.company.dto.CompanyUpdate
import de.byteleaf.companyon.company.dto.input.CompanyInput
import graphql.kickstart.tools.GraphQLMutationResolver
import graphql.kickstart.tools.GraphQLQueryResolver
import graphql.kickstart.tools.GraphQLSubscriptionResolver
import org.reactivestreams.Publisher
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller

@Controller
class CompanyResolver : GraphQLMutationResolver, GraphQLQueryResolver, GraphQLSubscriptionResolver {

    @Autowired
    private lateinit var companyAccessService: CompanyAccessService

    fun getCompany(id: String): Company = companyAccessService.get(id)

    fun getCompanies(): List<Company> = companyAccessService.findAll()

    fun createCompany(input: CompanyInput): Company = companyAccessService.create(input)

    fun updateCompany(id: String, input: CompanyInput): Company = companyAccessService.update(id, input)

    fun deleteCompany(id: String): Company = companyAccessService.delete(id)

    fun companyUpdate(): Publisher<CompanyUpdate> = companyAccessService.getPublisher()
}
