package de.byteleaf.companyon.user.dto.input

import de.byteleaf.companyon.fileupload.dto.input.FileMetaInput


data class UserInput(
        val oauth2Subject: String,
        val firstName: String,
        val lastName: String,
        val email: String,
        var signature: FileMetaInput? = null,
        var avatar: FileMetaInput? = null
)
