package de.byteleaf.companyon.project.dto

import de.byteleaf.companyon.common.configuration.NoArgConstructor
import de.byteleaf.companyon.common.dto.BaseDTO
import de.byteleaf.companyon.company.dto.Company

@NoArgConstructor
data class Project(val name: String, val company: Company) : BaseDTO()
