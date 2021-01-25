package de.byteleaf.companyon.common.configuration

import de.byteleaf.companyon.common.db.OffsetDateTimeReadConverter
import de.byteleaf.companyon.common.db.OffsetDateTimeWriteConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.core.convert.MongoCustomConversions
import java.util.Arrays.asList


@Configuration
class MongoConfiguration {

      @Bean
    fun customConversions(): MongoCustomConversions? {
        return MongoCustomConversions(asList(OffsetDateTimeReadConverter(), OffsetDateTimeWriteConverter()))
    }
}