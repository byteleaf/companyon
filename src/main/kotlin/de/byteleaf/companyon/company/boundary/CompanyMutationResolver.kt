package de.byteleaf.companyon.company.boundary

import de.byteleaf.companyon.company.control.CompanyService
import de.byteleaf.companyon.company.dto.Company
import de.byteleaf.companyon.company.dto.input.CompanyInput
import graphql.kickstart.tools.GraphQLMutationResolver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller


@Controller
class CompanyMutationResolver : GraphQLMutationResolver {
    @Autowired
    private lateinit var companyService: CompanyService

    fun updateCompany(id: String, input: CompanyInput): Company = companyService.update(id, input)

    fun createCompany(input: CompanyInput): Company = companyService.create(input)

    fun deleteCompany(id: String): Company = companyService.delete(id)
}