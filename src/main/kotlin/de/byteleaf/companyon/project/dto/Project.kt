package de.byteleaf.companyon.project.dto

import de.byteleaf.companyon.common.configuration.NoArgConstructor
import de.byteleaf.companyon.common.dto.BaseDTO
import de.byteleaf.companyon.company.dto.Company
import de.byteleaf.companyon.project.entity.ProjectState

@NoArgConstructor
data class Project(val name: String, val company: Company, val state: ProjectState) : BaseDTO()
