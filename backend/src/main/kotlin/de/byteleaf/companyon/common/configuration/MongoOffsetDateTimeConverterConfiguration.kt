package de.byteleaf.companyon.common.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.convert.converter.Converter
import org.springframework.data.mongodb.core.convert.MongoCustomConversions
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.util.*


@Configuration
class MongoOffsetDateTimeConverterConfiguration {

    @Bean
    fun mongoCustomConversions(): MongoCustomConversions? {
        return MongoCustomConversions(
            listOf(
                OffsetDateTimeReadConverter(),
                OffsetDateTimeWriteConverter()
            )
        )
    }

    internal class OffsetDateTimeWriteConverter : Converter<OffsetDateTime, Date> {
        override fun convert(source: OffsetDateTime): Date {
            return Date.from(source.toInstant())
        }
    }

    internal class OffsetDateTimeReadConverter : Converter<Date, OffsetDateTime> {
        override fun convert(source: Date): OffsetDateTime {
            return source.toInstant().atZone(ZoneOffset.UTC).toOffsetDateTime()
        }
    }
}