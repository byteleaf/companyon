package de.byteleaf.companyon.common.error.exception

import de.byteleaf.companyon.common.entity.EntityType
import de.byteleaf.companyon.common.error.ErrorCode

class EntityNotFoundException(errorCode: ErrorCode, val id: String, val entityType: EntityType) : AbstractException(errorCode,
        "Entity with id $id and type ${entityType.name} could not be found.")