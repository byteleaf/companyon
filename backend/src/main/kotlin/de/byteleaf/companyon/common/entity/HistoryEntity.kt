package de.byteleaf.companyon.common.entity

import de.byteleaf.companyon.common.annotation.NoArgConstructor
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.time.OffsetDateTime
import javax.swing.text.html.parser.Entity

@NoArgConstructor
@Document(collection = "history")
class HistoryEntity(
    val entity: Entity,
    @Indexed
    val type: EntityType,
    @Indexed
    val validFrom: OffsetDateTime,
    @Indexed
    val entityId: String
) {
    @Id
    var id: String? = null
}