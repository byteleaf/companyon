package de.byteleaf.companyon.project.dto

import de.byteleaf.companyon.common.configuration.NoArgConstructor
import de.byteleaf.companyon.common.dto.BaseDTO

@NoArgConstructor
data class Project(val name: String) : BaseDTO()
