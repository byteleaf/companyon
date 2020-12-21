package de.byteleaf.companyon.project.converter

import de.byteleaf.companyon.company.entity.CompanyEntity
import de.byteleaf.companyon.project.dto.input.ProjectInput
import de.byteleaf.companyon.project.entity.ProjectEntity
import org.modelmapper.AbstractConverter

class ProjectInputToEntityConverter : AbstractConverter<ProjectInput, ProjectEntity>() {
    override fun convert(source: ProjectInput): ProjectEntity {
        val companyEntity = CompanyEntity("") // TODO should we get the company entity from the DB?
        companyEntity.id = source.company

        return ProjectEntity(
                source.name,
                companyEntity
        )
    }

}