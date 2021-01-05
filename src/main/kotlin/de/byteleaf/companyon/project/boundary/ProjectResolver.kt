package de.byteleaf.companyon.project.boundary

import de.byteleaf.companyon.project.control.ProjectService
import de.byteleaf.companyon.project.dto.Project
import de.byteleaf.companyon.project.dto.ProjectUpdate
import de.byteleaf.companyon.project.dto.input.ProjectInput
import graphql.kickstart.tools.GraphQLMutationResolver
import graphql.kickstart.tools.GraphQLQueryResolver
import graphql.kickstart.tools.GraphQLSubscriptionResolver
import org.reactivestreams.Publisher
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller

@Controller
class ProjectResolver : GraphQLQueryResolver, GraphQLMutationResolver, GraphQLSubscriptionResolver {

    @Autowired
    private lateinit var projectService: ProjectService

    fun getProject(id: String): Project = projectService.get(id)

    fun getProjects(companies: Collection<String>?): List<Project> = projectService.findAll(companies)

    fun createProject(input: ProjectInput): Project = projectService.create(input)

    fun updateProject(id: String, input: ProjectInput): Project = projectService.update(id, input)

    fun deleteProject(id: String): Project = projectService.delete(id)

    fun projectUpdate(): Publisher<ProjectUpdate> = projectService.getPublisher()
}