package de.byteleaf.companyon.project.boundary

import de.byteleaf.companyon.project.access.ProjectAccessService
import de.byteleaf.companyon.project.dto.Project
import de.byteleaf.companyon.timelog.dto.TimeLog
import graphql.kickstart.tools.GraphQLResolver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import java.util.*

@Controller
class TimeLogProjectFieldResolver : GraphQLResolver<TimeLog> {

    @Autowired
    private lateinit var projectAccessService: ProjectAccessService

    fun getProject(timeLog: TimeLog): Optional<Project> = projectAccessService.getNullable(timeLog.project)
}