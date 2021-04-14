package de.byteleaf.companyon.auth.dto

import de.byteleaf.companyon.common.annotation.NoArgConstructor
import java.time.OffsetDateTime

@NoArgConstructor
class Approval(
    val user: String,
    val timeStamp: OffsetDateTime
)