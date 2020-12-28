package de.byteleaf.companyon.project.control

import de.byteleaf.companyon.common.control.AbstractDataService
import de.byteleaf.companyon.common.event.EntityDeletedEvent
import de.byteleaf.companyon.common.entity.EntityType
import de.byteleaf.companyon.company.dto.Company
import de.byteleaf.companyon.project.dto.Project
import de.byteleaf.companyon.project.dto.input.ProjectInput
import de.byteleaf.companyon.project.entity.ProjectEntity
import de.byteleaf.companyon.project.respository.ProjectRepository
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

@Service
class ProjectService : AbstractDataService<ProjectEntity, Project, ProjectInput, ProjectRepository>() {

    override fun getEntityType(): EntityType = EntityType.PROJECT

    @EventListener(condition = "#event.entityType == T(de.byteleaf.companyon.common.entity.EntityType).COMPANY")
    fun deleteByCompany(event: EntityDeletedEvent<*>) {
        repository.deleteByCompany(event.entity.id!!)
    }

    fun findByCompany(companyId: String): List<Project> = repository.findByCompany(companyId).map { entityToOutput(it) }
}