package de.byteleaf.companyon.company.boundary

import de.byteleaf.companyon.company.access.CompanyAccessService
import de.byteleaf.companyon.company.dto.Company
import de.byteleaf.companyon.project.dto.Project
import graphql.kickstart.tools.GraphQLResolver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import java.util.*

@Controller
class ProjectCompanyFieldResolver : GraphQLResolver<Project> {

    @Autowired
    private lateinit var companyAccessService: CompanyAccessService

    fun getCompany(project: Project): Optional<Company> = companyAccessService.getNullable(project.company)
}
