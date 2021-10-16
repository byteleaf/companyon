package de.byteleaf.companyon.project.dto

import de.byteleaf.companyon.common.annotation.NoArgConstructor
import de.byteleaf.companyon.common.dto.BaseDTO
import de.byteleaf.companyon.project.entity.ProjectState

@NoArgConstructor
data class Project(
    override val id: String,
    val name: String,
    /**
     * @see de.byteleaf.companyon.company.boundary.ProjectCompanyFieldResolver.getCompany
     */
    val company: String,
    val state: ProjectState
) : BaseDTO()
