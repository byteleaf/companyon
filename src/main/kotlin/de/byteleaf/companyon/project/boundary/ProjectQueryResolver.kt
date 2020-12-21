package de.byteleaf.companyon.project.boundary

import de.byteleaf.companyon.common.boundary.GraphQLQueryResolver
import de.byteleaf.companyon.project.control.ProjectService
import de.byteleaf.companyon.project.dto.Project
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller

@Controller
class ProjectQueryResolver : GraphQLQueryResolver() {

    @Autowired
    private lateinit var projectService: ProjectService

    fun getProject(id: String): Project? = throwIfEmpty(projectService.get(id), mapOf("id" to id))

    fun getProjects(): List<Project> = projectService.findAll()
}
