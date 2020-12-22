package de.byteleaf.companyon.company.boundary

import graphql.kickstart.tools.GraphQLQueryResolver;
import de.byteleaf.companyon.company.control.CompanyService
import de.byteleaf.companyon.company.dto.Company
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller

@Controller
class CompanyQueryResolver : GraphQLQueryResolver {

    @Autowired
    private lateinit var companyService: CompanyService

    fun getCompany(id: String): Company? = companyService.get(id)

    fun getCompanies(): List<Company> = companyService.findAll()
}
