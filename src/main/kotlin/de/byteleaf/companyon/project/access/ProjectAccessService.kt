package de.byteleaf.companyon.project.access

import de.byteleaf.companyon.project.dto.Project
import de.byteleaf.companyon.project.dto.ProjectUpdate
import de.byteleaf.companyon.project.dto.input.ProjectInput
import de.byteleaf.companyon.project.logic.ProjectService
import org.reactivestreams.Publisher
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProjectAccessService {

    @Autowired
    private lateinit var projectService: ProjectService

    fun get(id: String): Project = projectService.get(id)

    fun findAll(companies: Collection<String>?): List<Project> = projectService.findAll(companies)

    fun create(input: ProjectInput): Project = projectService.create(input)

    fun update(id: String, input: ProjectInput): Project = projectService.update(id, input)

    fun delete(id: String): Project = projectService.delete(id)

    fun getPublisher(): Publisher<ProjectUpdate> = projectService.getPublisher()
}