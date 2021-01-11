package de.byteleaf.companyon.project.control

import de.byteleaf.companyon.common.control.AbstractEventDataService
import de.byteleaf.companyon.common.event.EntityDeletedEvent
import de.byteleaf.companyon.common.entity.EntityType
import de.byteleaf.companyon.company.control.CompanyService
import de.byteleaf.companyon.project.dto.Project
import de.byteleaf.companyon.project.dto.ProjectUpdate
import de.byteleaf.companyon.project.dto.input.ProjectInput
import de.byteleaf.companyon.project.entity.ProjectEntity
import de.byteleaf.companyon.project.respository.ProjectRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProjectService : AbstractEventDataService<ProjectEntity, Project, ProjectUpdate, ProjectInput, ProjectRepository>() {

    override fun getEntityType(): EntityType = EntityType.PROJECT

    @Autowired
    private lateinit var companyService: CompanyService

    /**
     * If a company was deleted, all projects assigned to this company have to be deleted too!
     */
    @EventListener(condition = "#event.entityType == T(de.byteleaf.companyon.common.entity.EntityType).COMPANY")
    fun onCompanyDeleted(event: EntityDeletedEvent<*>) {
        repository.deleteByCompany(event.entity.id!!)
    }

    fun findAll(companies: Collection<String>?): List<Project> =
        Optional.ofNullable(companies).map { repository.findByCompanyIn(it).map { entityToOutput(it) } }
            .orElseGet { super.findAll() }

    override fun inputToEntity(input: ProjectInput): ProjectEntity {
        val entity = modelMapper.map(input, ProjectEntity::class.java)
        entity.company = companyService.getEntity(input.company)
        return entity
    }
}