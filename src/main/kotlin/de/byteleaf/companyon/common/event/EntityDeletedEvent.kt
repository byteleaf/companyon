package de.byteleaf.companyon.common.event

import de.byteleaf.companyon.common.entity.EntityType

data class EntityDeletedEvent(val entityType: EntityType, val id: String)