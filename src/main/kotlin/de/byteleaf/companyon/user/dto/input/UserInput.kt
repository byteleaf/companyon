package de.byteleaf.companyon.user.dto.input

import de.byteleaf.companyon.fileupload.dto.input.FileMetaInput


data class UserInput(
    val firstName: String,
    val lastName: String,
    val email: String,
    val signature: FileMetaInput? = null,
    val avatar: FileMetaInput? = null
)
