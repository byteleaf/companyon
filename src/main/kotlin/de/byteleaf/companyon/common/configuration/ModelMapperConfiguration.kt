package de.byteleaf.companyon.common.configuration

import org.modelmapper.ModelMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ModelMapperConfiguration {

    @Bean
    fun createModelMapper(): ModelMapper {
        val modelMapper = ModelMapper()
        modelMapper.configuration
                .setSkipNullEnabled(true).isFieldMatchingEnabled = true
        return modelMapper
    }
}
