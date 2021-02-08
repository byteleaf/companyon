package de.byteleaf.companyon.project.dto

import de.byteleaf.companyon.common.dto.EntityUpdateType

class TimeLogUpdateGQLResponse(
    var type: EntityUpdateType,
    var entity: TimeLogGQLResponse
)