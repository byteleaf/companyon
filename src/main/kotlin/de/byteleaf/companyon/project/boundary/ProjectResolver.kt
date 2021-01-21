package de.byteleaf.companyon.project.boundary

import de.byteleaf.companyon.project.access.ProjectAccessService
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
    private lateinit var projectAccessService: ProjectAccessService

    fun getProject(id: String): Project = projectAccessService.get(id)

    fun getProjects(companies: Collection<String>?): List<Project> = projectAccessService.findAll(companies)

    fun createProject(input: ProjectInput): Project = projectAccessService.create(input)

    fun updateProject(id: String, input: ProjectInput): Project = projectAccessService.update(id, input)

    fun deleteProject(id: String): Project = projectAccessService.delete(id)

    fun projectUpdate(): Publisher<ProjectUpdate> = projectAccessService.getPublisher()
}