package de.byteleaf.companyon.company.boundary

import de.byteleaf.companyon.common.boundary.GraphQLQueryResolver
import de.byteleaf.companyon.company.control.CompanyService
import de.byteleaf.companyon.company.dto.Company
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller

@Controller
class CompanyQueryResolver : GraphQLQueryResolver() {

    @Autowired
    private lateinit var companyService: CompanyService

    fun getCompany(id: String): Company? = throwIfEmpty(companyService.get(id), mapOf("id" to id))

    fun getCompanies(): List<Company> = companyService.findAll()
}
