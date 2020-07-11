package de.byteleaf.companyon.project.entity

import de.byteleaf.companyon.common.entity.BaseEntity
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "projects")
data class ProjectEntity(
        var name: String? = null
) : BaseEntity()