package de.byteleaf.companyon.project.control

import de.byteleaf.companyon.common.control.AbstractDataService
import de.byteleaf.companyon.common.event.EntityDeletedEvent
import de.byteleaf.companyon.common.entity.EntityType
import de.byteleaf.companyon.common.util.GenericSupportUtil
import de.byteleaf.companyon.company.control.CompanyService
import de.byteleaf.companyon.company.dto.Company
import de.byteleaf.companyon.company.entity.CompanyEntity
import de.byteleaf.companyon.project.dto.Project
import de.byteleaf.companyon.project.dto.input.ProjectInput
import de.byteleaf.companyon.project.entity.ProjectEntity
import de.byteleaf.companyon.project.respository.ProjectRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

@Service
class ProjectService : AbstractDataService<ProjectEntity, Project, ProjectInput, ProjectRepository>() {

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

    fun findAll(companies: Collection<String>?): List<Project> = if(companies != null)
        repository.findByCompanyIn(companies).map { entityToOutput(it) } else super.findAll()

    override fun inputToEntity(input: ProjectInput): ProjectEntity {
        val entity = modelMapper.map(input, ProjectEntity::class.java)
        entity.company = companyService.getEntity(input.company)
        return entity
    }
}