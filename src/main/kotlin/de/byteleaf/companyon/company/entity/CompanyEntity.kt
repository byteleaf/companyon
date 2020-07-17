package de.byteleaf.companyon.company.entity

import de.byteleaf.companyon.common.configuration.NoArgConstructor
import de.byteleaf.companyon.common.entity.BaseEntity
import org.springframework.data.mongodb.core.mapping.Document

@NoArgConstructor
@Document(collection = "companies")
data class CompanyEntity(val name: String) : BaseEntity()
