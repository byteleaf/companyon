package de.byteleaf.companyon.common.event

import de.byteleaf.companyon.common.dto.BaseDTO
import de.byteleaf.companyon.common.entity.EntityType

class EntityUpdatedEvent<T : BaseDTO>(entityType: EntityType, entity: T) : EntityEvent<T>(entityType, entity, EventType.UPDATED)