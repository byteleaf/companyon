package de.byteleaf.companyon.project.entity

import de.byteleaf.companyon.common.annotation.NoArgConstructor
import de.byteleaf.companyon.common.entity.BaseEntity
import org.springframework.data.mongodb.core.mapping.Document

@NoArgConstructor
@Document(collection = "projects")
data class ProjectEntity(
    val name: String,
    val state: ProjectState,
    var company: String
) : BaseEntity()
