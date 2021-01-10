package de.byteleaf.companyon.user.dto

import de.byteleaf.companyon.common.configuration.NoArgConstructor
import de.byteleaf.companyon.common.dto.BaseDTO
import de.byteleaf.companyon.fileupload.dto.FileMeta

@NoArgConstructor
data class User(
    val oauth2Subject: String?,
    val firstName: String,
    val lastName: String,
    val email: String,
    val admin: Boolean = false,
    val signature: FileMeta?,
    val avatar: FileMeta?
) : BaseDTO()
