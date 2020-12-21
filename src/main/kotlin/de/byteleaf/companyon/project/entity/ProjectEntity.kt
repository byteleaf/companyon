package de.byteleaf.companyon.project.entity

import de.byteleaf.companyon.common.configuration.NoArgConstructor
import de.byteleaf.companyon.common.entity.BaseEntity
import de.byteleaf.companyon.company.entity.CompanyEntity
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document

@NoArgConstructor
@Document(collection = "projects")
data class ProjectEntity(
        val name: String,
        @DBRef val company: CompanyEntity
) : BaseEntity()
