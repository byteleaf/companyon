package de.byteleaf.companyon.task.dto

import de.byteleaf.companyon.common.dto.EntityUpdateType

class TaskUpdateGQLResponse(
    var type: EntityUpdateType,
    var entity: TaskGQLResponse
)