package de.byteleaf.companyon.project.boundary

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import de.byteleaf.companyon.project.control.ProjectService
import de.byteleaf.companyon.project.dto.Project
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller

@Controller
class ProjectQueryResolver : GraphQLQueryResolver {

    @Autowired
    private lateinit var projectService: ProjectService

    fun getProject(id: String): Project = projectService.get(id)

    fun getProjects(): List<Project> = projectService.findAll()
}
