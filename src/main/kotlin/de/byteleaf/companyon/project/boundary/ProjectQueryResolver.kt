package de.byteleaf.companyon.project.boundary

import de.byteleaf.companyon.project.control.ProjectService
import de.byteleaf.companyon.project.dto.Project
import graphql.kickstart.tools.GraphQLQueryResolver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller

@Controller
class ProjectQueryResolver : GraphQLQueryResolver {

    @Autowired
    private lateinit var projectService: ProjectService

    fun getProject(id: String): Project? = getProject(id)

    fun getProjects(companies: Collection<String>?): List<Project> = projectService.findAll(companies)
}
