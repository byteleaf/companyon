package de.byteleaf.companyon.test.util

import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

class DateTimeUtil {
    companion object {
        fun getDateTime(dt: String): OffsetDateTime =
            OffsetDateTime.parse(dt, DateTimeFormatter.ISO_OFFSET_DATE_TIME)
    }
}
