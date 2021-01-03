package de.byteleaf.companyon.user.entity

import de.byteleaf.companyon.common.configuration.NoArgConstructor
import de.byteleaf.companyon.common.entity.BaseEntity
import de.byteleaf.companyon.fileupload.dto.FileMeta
import org.springframework.data.mongodb.core.mapping.Document

@NoArgConstructor
@Document(collection = "users")
data class UserEntity(
        var oauth2Subject: String? = null,
        val firstName: String,
        val lastName: String,
        val email: String,
        val signature: FileMeta?,
        val avatar: FileMeta?
) : BaseEntity()
