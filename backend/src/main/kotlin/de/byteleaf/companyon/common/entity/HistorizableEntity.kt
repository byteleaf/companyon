package de.byteleaf.companyon.common.entity

import java.time.OffsetDateTime

abstract class HistorizableEntity(val activeFrom: OffsetDateTime) : BaseEntity() {
}