package de.byteleaf.companyon.company.boundary

import de.byteleaf.companyon.company.control.CompanyService
import de.byteleaf.companyon.company.dto.Company
import de.byteleaf.companyon.company.dto.input.CompanyInput
import de.byteleaf.companyon.project.control.ProjectService
import graphql.kickstart.tools.GraphQLMutationResolver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller


@Controller
class CompanyMutationResolver : GraphQLMutationResolver {
    @Autowired
    private lateinit var companyService: CompanyService
    @Autowired
    private lateinit var projectService: ProjectService

    fun updateCompany(id: String, input: CompanyInput): Company = companyService.update(id, input)

    fun createCompany(input: CompanyInput): Company = companyService.create(input)

    fun deleteCompany(id: String): Boolean {
        companyService.delete(id)
        projectService.deleteByCompany(id)
        return true
    }
}