package de.byteleaf.companyon.common.entity

import de.byteleaf.companyon.common.annotation.NoArgConstructor
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.time.OffsetDateTime

@NoArgConstructor
@Document(collection = "history")
class HistoryEntity(
    @Indexed
    val type: EntityType,
    @Indexed
    val validFrom: OffsetDateTime,
    @Indexed
    val entityId: String,

    val entity: Any
) {
    @Id
    var id: String? = null
}