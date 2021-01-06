package de.byteleaf.companyon.user.dto

import de.byteleaf.companyon.common.configuration.NoArgConstructor
import de.byteleaf.companyon.common.dto.BaseDTO
import de.byteleaf.companyon.fileupload.dto.FileMeta

@NoArgConstructor
data class User(
        val oAuth2Subject: String?,
        val firstName: String,
        val lastName: String,
        val email: String,
        val signature: FileMeta?,
        val avatar: FileMeta?
) : BaseDTO()
