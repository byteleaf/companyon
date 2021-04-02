package de.byteleaf.companyon.project.dto

import de.byteleaf.companyon.common.dto.EntityUpdateType

class ProjectUpdateGQLResponse(
    var type: EntityUpdateType,
    var entity: ProjectGQLResponse
)
