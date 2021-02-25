package de.byteleaf.companyon.project.logic

import de.byteleaf.companyon.common.logic.AbstractEventDataService
import de.byteleaf.companyon.common.entity.EntityType
import de.byteleaf.companyon.common.event.EntityDeletedEvent
import de.byteleaf.companyon.project.dto.Project
import de.byteleaf.companyon.project.dto.ProjectUpdate
import de.byteleaf.companyon.project.dto.ProjectInput
import de.byteleaf.companyon.project.entity.ProjectEntity
import de.byteleaf.companyon.project.respository.ProjectRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProjectService :
    AbstractEventDataService<ProjectEntity, Project, ProjectUpdate, ProjectInput, ProjectRepository>() {

    override fun getEntityType(): EntityType = EntityType.PROJECT

    fun findAll(companies: Collection<String>?): List<Project> =
        Optional.ofNullable(companies).map { repository.findByCompanyIn(it).map { entityToOutput(it) } }
            .orElseGet { super.findAll() }

    fun deleteByCompany(companyId: String) {
        repository.deleteByCompany(companyId).forEach {
            applicationEventPublisher.publishEvent(EntityDeletedEvent(getEntityType(), entityToOutput(it)))
        }
    }
}