package de.byteleaf.companyon.common.dto

import de.byteleaf.companyon.common.event.EventType

enum class EntityUpdateType(val eventType: EventType) {
    DELETED(EventType.DELETED),
    UPDATED(EventType.UPDATED),
    CREATED(EventType.CREATED);

    companion object {
        fun getByEventType(targetEventType: EventType): EntityUpdateType {
            for (entry in EntityUpdateType.values()) {
                if (entry.eventType == targetEventType) {
                    return entry
                }
            }
            throw RuntimeException("No matching EntityUpdateType found for this event")
        }
    }
}
