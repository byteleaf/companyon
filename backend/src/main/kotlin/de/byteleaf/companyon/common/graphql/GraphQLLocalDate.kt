package de.byteleaf.companyon.common.graphql

import graphql.language.StringValue
import graphql.schema.*
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

// TODO https://github.com/byteleaf/companyon/issues/70
@Suppress("DEPRECATION")
@Component
class GraphQLLocalDate @JvmOverloads constructor(name: String? = "LocalDate") :
    GraphQLScalarType(name, "LocalDate", object : Coercing<LocalDate, String> {
        private fun convertImpl(input: Any): LocalDate? {
            if (input is String) {
                try {
                    return LocalDate.parse(input)
                } catch (ignored: DateTimeParseException) {
                    // nothing to-do
                }
            } else if (input is LocalDate) {
                return input
            }
            return null
        }

        override fun serialize(input: Any): String {
            return if (input is LocalDate) {
                DateTimeFormatter.ISO_LOCAL_DATE.format(input)
            } else {
                val result = convertImpl(input) ?: throw CoercingSerializeException("Invalid value '$input' for LocalDate")
                DateTimeFormatter.ISO_LOCAL_DATE.format(result)
            }
        }

        override fun parseValue(input: Any): LocalDate {
            return convertImpl(input) ?: throw CoercingParseValueException("Invalid value '$input' for LocalDate")
        }

        override fun parseLiteral(input: Any): LocalDate {
            val value = (input as StringValue).value
            return convertImpl(value)
                ?: throw CoercingParseLiteralException("Invalid value '$input' for LocalDate")
        }
    })