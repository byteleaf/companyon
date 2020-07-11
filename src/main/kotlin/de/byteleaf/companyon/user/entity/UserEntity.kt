package de.byteleaf.companyon.user.entity

import de.byteleaf.companyon.common.entity.BaseEntity
import de.byteleaf.companyon.fileupload.dto.FileMeta
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "users")
data class UserEntity(
        var firstName: String? = null,
        var lastName: String? = null,
        var signature: FileMeta? = null,
        var avatar: FileMeta? = null
) : BaseEntity()