package de.byteleaf.companyon.common.db

import org.springframework.core.convert.converter.Converter
import java.time.OffsetDateTime
import java.util.*

class OffsetDateTimeWriteConverter : Converter<OffsetDateTime, Date> {

    override fun convert(offsetDateTime: OffsetDateTime): Date? {
        return Date.from(offsetDateTime.toInstant());
    }
}