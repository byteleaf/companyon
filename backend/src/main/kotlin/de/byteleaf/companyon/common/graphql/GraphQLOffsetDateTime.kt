package de.byteleaf.companyon.common.graphql

import graphql.language.StringValue
import graphql.schema.*
import org.springframework.stereotype.Component
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

// TODO fix deprecation
@Suppress("DEPRECATION")
@Component
class GraphQLOffsetDateTime @JvmOverloads constructor(name: String? = DEFAULT_NAME) :
    GraphQLScalarType(
        name, "OffsetDateTime",
        object : Coercing<OffsetDateTime, String> {
            private fun convertImpl(input: Any): OffsetDateTime? {
                if (input is String) {
                    try {
                        return OffsetDateTime.parse(input, DateTimeFormatter.ISO_OFFSET_DATE_TIME)
                    } catch (ignored: DateTimeParseException) {
                        // nothing to-do
                    }
                } else if (input is OffsetDateTime) {
                    return input
                }
                return null
            }

            override fun serialize(input: Any): String {
                return if (input is OffsetDateTime) {
                    DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(input)
                } else {
                    val result =
                        convertImpl(input) ?: throw CoercingSerializeException("Invalid value '$input' for OffsetDateTime")
                    DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(result)
                }
            }

            override fun parseValue(input: Any): OffsetDateTime {
                return convertImpl(input) ?: throw CoercingParseValueException("Invalid value '$input' for OffsetDateTime")
            }

            override fun parseLiteral(input: Any): OffsetDateTime {
                val value = (input as StringValue).value
                return convertImpl(value)
                    ?: throw CoercingParseLiteralException("Invalid value '$input' for OffsetDateTime")
            }
        }
    ) {
    companion object {
        private const val DEFAULT_NAME = "OffsetDateTime"
    }
}
