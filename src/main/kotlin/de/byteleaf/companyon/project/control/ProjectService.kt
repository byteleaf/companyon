package de.byteleaf.companyon.project.control

import de.byteleaf.companyon.common.control.AbstractDataService
import de.byteleaf.companyon.project.dto.Project
import de.byteleaf.companyon.project.dto.input.ProjectInput
import de.byteleaf.companyon.project.entity.ProjectEntity
import de.byteleaf.companyon.project.respository.ProjectRepository
import org.springframework.stereotype.Service

@Service
class ProjectService : AbstractDataService<ProjectEntity, Project, ProjectInput, ProjectRepository>()