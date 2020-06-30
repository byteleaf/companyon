package de.byteleaf.companyon.common.entity

import org.springframework.data.annotation.Id

abstract class AbstractEntity {
    @Id
    var id: Long = 0
}