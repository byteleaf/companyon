package de.byteleaf.companyon.common.entity

import org.springframework.data.annotation.*
import java.util.*

abstract class BaseEntity {
    @Id
    var id: String? = null

    @CreatedBy
    lateinit var createdBy: String

    @CreatedDate
    lateinit var createdDate: Date

    @LastModifiedBy
    lateinit var updateBy: String

    @LastModifiedDate
    lateinit var updateDate: Date

    var active: Boolean = true

    var deleted: Boolean = false
}
