package de.byteleaf.companyon.project.boundary

import de.byteleaf.companyon.project.control.ProjectService
import de.byteleaf.companyon.project.dto.ProjectUpdated
import org.reactivestreams.Publisher
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller

@Controller
class ProjectSubscriptionResolver {

    @Autowired
    private lateinit var projectService: ProjectService

    fun projectUpdated(): Publisher<ProjectUpdated> = projectService.getPublisher()
}