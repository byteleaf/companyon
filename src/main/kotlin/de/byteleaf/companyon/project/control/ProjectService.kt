package de.byteleaf.companyon.project.control

import de.byteleaf.companyon.common.control.AbstractDataService
import de.byteleaf.companyon.common.event.EntityCreatedEvent
import de.byteleaf.companyon.common.event.EntityDeletedEvent
import de.byteleaf.companyon.common.event.EventEntityType
import de.byteleaf.companyon.company.entity.CompanyEntity
import de.byteleaf.companyon.project.dto.Project
import de.byteleaf.companyon.project.dto.input.ProjectInput
import de.byteleaf.companyon.project.entity.ProjectEntity
import de.byteleaf.companyon.project.respository.ProjectRepository
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

@Service
class ProjectService : AbstractDataService<ProjectEntity, Project, ProjectInput, ProjectRepository>() {

    override fun getEventEntityType(): EventEntityType = EventEntityType.PROJECT

    @EventListener(condition = "#event.entityType == T(de.byteleaf.companyon.common.event.EventEntityType).COMPANY")
    fun deleteByCompany(event: EntityDeletedEvent<*>) {
        repository.deleteByCompany(event.id)
    }
}