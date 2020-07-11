package de.byteleaf.companyon.project.boundary

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import de.byteleaf.companyon.project.control.ProjectService
import de.byteleaf.companyon.project.dto.Project
import de.byteleaf.companyon.project.dto.input.ProjectInput
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller


@Controller
class ProjectMutationResolver : GraphQLMutationResolver {
    @Autowired
    private lateinit var projectService: ProjectService

    fun updateProject(id: String, input: ProjectInput): Project = projectService.update(id, input)

    fun createProject(input: ProjectInput): Project = projectService.create(input)

    // TODO implement Skalar Void: https://byteleaf.atlassian.net/browse/COM-36
    fun deleteProject(id: String): Boolean {
        projectService.delete(id)
        return true
    }
}