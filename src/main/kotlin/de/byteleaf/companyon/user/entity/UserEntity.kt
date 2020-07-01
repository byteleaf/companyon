package de.byteleaf.companyon.user.entity

import de.byteleaf.companyon.common.entity.AbstractEntity
import de.byteleaf.companyon.fileupload.dto.File
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "users")
data class UserEntity(
        var firstName: String? = null,
        var lastName: String? = null,
        var signature: File? = null,
        val avatar: File? = null
) : AbstractEntity()