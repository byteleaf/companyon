package de.byteleaf.companyon.common.dto

import de.byteleaf.companyon.common.event.EventType

enum class EntityUpdateType(val eventType: EventType) {
    DELETED(EventType.DELETED),
    UPDATED(EventType.UPDATED),
    CREATED(EventType.CREATED);

    companion object {
        fun getByEventType(targetEventType: EventType): EntityUpdateType =
            values().find { it.eventType == targetEventType }
                ?: throw RuntimeException("No matching EntityUpdateType found for this event")
    }
}
