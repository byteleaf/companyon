package de.byteleaf.companyon.common.entity

import org.springframework.data.annotation.Id

abstract class BaseEntity {
    @Id
    var id: String? = null
}
