package de.byteleaf.companyon.company.boundary

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import de.byteleaf.companyon.company.control.CompanyService
import de.byteleaf.companyon.company.dto.Company
import de.byteleaf.companyon.company.dto.input.CompanyInput
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller


@Controller
class CompanyMutationResolver : GraphQLMutationResolver {
    @Autowired
    private lateinit var companyService: CompanyService

    fun updateCompany(id: String, input: CompanyInput): Company = companyService.update(id, input)

    fun createCompany(input: CompanyInput): Company = companyService.create(input)

    // TODO implement Skalar Void: https://byteleaf.atlassian.net/browse/COM-36
    fun deleteCompany(id: String): Boolean {
        companyService.delete(id)
        return true
    }
}