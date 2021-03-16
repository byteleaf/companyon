package de.byteleaf.companyon.project.dto

import de.byteleaf.companyon.user.dto.UserGQLResponse

class TimeLogGQLResponse(
    var id: String?,
    var user: UserGQLResponse?,
    val project: ProjectGQLResponse?,
    val start: String?,
    val description: String?,
    val durationInMinutes: Int?,
    val breakInMinutes: Int?
)