package de.byteleaf.companyon.common.entity

import org.springframework.data.annotation.Id
import java.time.OffsetDateTime

abstract class BaseEntity {
    @Id
    var id: String? = null

    var validFrom: OffsetDateTime? = null

    var deleted = false
}
