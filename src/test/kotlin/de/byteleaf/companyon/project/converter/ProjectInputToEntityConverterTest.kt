package de.byteleaf.companyon.project.converter

import de.byteleaf.companyon.project.dto.input.ProjectInput
import de.byteleaf.companyon.project.entity.ProjectEntity
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.modelmapper.AbstractConverter
import org.modelmapper.ModelMapper

class ProjectInputToEntityConverterTest {

    val cut: AbstractConverter<ProjectInput, ProjectEntity> = ProjectInputToEntityConverter()

    @Test
    fun testConvert() {
        val input = ProjectInput("byteleaf GmbH", "1")

        val mapper = ModelMapper()
        mapper.addConverter(cut)

        val output = mapper.map(input, ProjectEntity::class.java)

        assertThat(output.id).isNull()
        assertThat(output.name).isEqualTo(input.name)
        assertThat(output.company.id).isEqualTo(input.company)
    }
}