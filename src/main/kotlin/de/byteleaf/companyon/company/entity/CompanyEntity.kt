package de.byteleaf.companyon.company.entity

import de.byteleaf.companyon.common.entity.BaseEntity
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "companies")
data class CompanyEntity(
        var name: String? = null
) : BaseEntity()