package de.byteleaf.companyon.task.dto

import de.byteleaf.companyon.task.entity.TaskState
import de.byteleaf.companyon.user.dto.UserGQLResponse

class TaskGQLResponse(
    var id: String?,
    var user: UserGQLResponse?,
    var status: TaskState?,
    var description: String?
)
