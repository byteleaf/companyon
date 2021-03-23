package de.byteleaf.companyon.company.dto

import de.byteleaf.companyon.common.annotation.NoArgConstructor
import de.byteleaf.companyon.common.dto.BaseDTO

@NoArgConstructor
data class Company(override val id: String, val name: String) : BaseDTO()
