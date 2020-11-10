package de.byteleaf.companyon.company.dto

import de.byteleaf.companyon.common.configuration.NoArgConstructor
import de.byteleaf.companyon.common.dto.BaseDTO

@NoArgConstructor
data class Company(val name: String) : BaseDTO()
