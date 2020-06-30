package de.byteleaf.companyon.user.entity

import de.byteleaf.companyon.common.entity.AbstractEntity
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "users")
data class UserEntity(
        var firstName: String? = null,
        var lastName: String? = null
) : AbstractEntity()