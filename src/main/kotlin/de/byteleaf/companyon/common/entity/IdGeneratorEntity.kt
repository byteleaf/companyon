package de.byteleaf.companyon.common.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "id_generator")
data class IdGeneratorEntity(
        @Id
        val id: String? = null,
        val seq: Long = 0
)