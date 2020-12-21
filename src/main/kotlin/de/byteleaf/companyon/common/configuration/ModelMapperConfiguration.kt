package de.byteleaf.companyon.common.configuration

import de.byteleaf.companyon.project.converter.ProjectInputToEntityConverter
import org.modelmapper.ModelMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class ModelMapperConfiguration {

    @Bean
    fun createModelMapper(): ModelMapper {
        val modelMapper = ModelMapper()
        modelMapper.configuration
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setSkipNullEnabled(true).isFieldMatchingEnabled = true

        val projectInputToEntityConverter = ProjectInputToEntityConverter()

        modelMapper.addConverter(projectInputToEntityConverter)

        return modelMapper
    }
}
