package de.byteleaf.companyon.project.control

import de.byteleaf.companyon.common.control.AbstractDataService
import de.byteleaf.companyon.common.event.EntityDeletedEvent
import de.byteleaf.companyon.company.entity.CompanyEntity
import de.byteleaf.companyon.project.dto.Project
import de.byteleaf.companyon.project.dto.input.ProjectInput
import de.byteleaf.companyon.project.entity.ProjectEntity
import de.byteleaf.companyon.project.respository.ProjectRepository
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

@Service
class ProjectService : AbstractDataService<ProjectEntity, Project, ProjectInput, ProjectRepository>() {


    @EventListener(condition = "#event.eventEntityType instanceof T(de.byteleaf.companyon.company.entity.CompanyEntity)")
    fun handleSuccessful(event: EntityDeletedEvent) {
        println("Handling generic event (conditional).")
    }


    public fun deleteByCompany(companyId: String) {
        repository.deleteByCompany(companyId)
    }
}