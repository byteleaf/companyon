package de.byteleaf.companyon.project.boundary

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import de.byteleaf.companyon.common.errors.GraphQLException
import de.byteleaf.companyon.company.control.CompanyService
import de.byteleaf.companyon.project.control.ProjectService
import de.byteleaf.companyon.project.dto.Project
import de.byteleaf.companyon.project.dto.input.ProjectInput
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller


@Controller
class ProjectMutationResolver : GraphQLMutationResolver {
    @Autowired
    private lateinit var projectService: ProjectService

    @Autowired
    private lateinit var companyService: CompanyService

    fun updateProject(id: String, input: ProjectInput): Project = projectService.update(id, input)

    fun createProject(input: ProjectInput): Project {
        companyService.get(input.company) ?: throw GraphQLException("company not found",
                "common.errors.entityNotFound",
                mapOf("id" to input.company))

        return projectService.create(input)
    }

    fun deleteProject(id: String): Boolean {
        projectService.delete(id)
        return true
    }
}
