package de.byteleaf.companyon.user.dto

import de.byteleaf.companyon.fileupload.dto.FileMeta

class UserGQLResponse (
    val id: String?,
    val oauth2Subject: String?,
    val firstName: String?,
    val lastName: String?,
    val email: String?,
    val admin: Boolean?,
    val signature: FileMeta?,
    val avatar: FileMeta?
)