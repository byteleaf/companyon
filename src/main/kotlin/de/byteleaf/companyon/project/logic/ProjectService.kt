package de.byteleaf.companyon.project.logic

import de.byteleaf.companyon.common.control.AbstractEventDataService
import de.byteleaf.companyon.common.entity.EntityType
import de.byteleaf.companyon.company.logic.CompanyService
import de.byteleaf.companyon.project.dto.Project
import de.byteleaf.companyon.project.dto.ProjectUpdate
import de.byteleaf.companyon.project.dto.input.ProjectInput
import de.byteleaf.companyon.project.entity.ProjectEntity
import de.byteleaf.companyon.project.respository.ProjectRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProjectService :
    AbstractEventDataService<ProjectEntity, Project, ProjectUpdate, ProjectInput, ProjectRepository>() {

    override fun getEntityType(): EntityType = EntityType.PROJECT

    @Autowired
    private lateinit var companyService: CompanyService

    fun findAll(companies: Collection<String>?): List<Project> =
        Optional.ofNullable(companies).map { repository.findByCompanyIn(it).map { entityToOutput(it) } }
            .orElseGet { super.findAll() }

    override fun inputToEntity(input: ProjectInput): ProjectEntity {
        val entity = modelMapper.map(input, ProjectEntity::class.java)
        entity.company = companyService.getEntity(input.company)
        return entity
    }

    fun deleteByCompany(companyId: String) {
        repository.deleteByCompany(companyId)
    }
}