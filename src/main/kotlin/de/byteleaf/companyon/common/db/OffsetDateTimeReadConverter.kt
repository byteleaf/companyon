package de.byteleaf.companyon.common.db

import org.springframework.core.convert.converter.Converter
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.util.*

class OffsetDateTimeReadConverter : Converter<Date, OffsetDateTime> {

    override fun convert(date: Date): OffsetDateTime? {
        return date.toInstant().atZone(ZoneOffset.UTC).toOffsetDateTime()
    }
}