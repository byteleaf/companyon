package de.byteleaf.companyon.common.configuration

import de.byteleaf.companyon.fileupload.dto.File
import de.byteleaf.companyon.fileupload.dto.input.FileInput
import org.modelmapper.ModelMapper
import org.modelmapper.PropertyMap
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ModelMapperConfiguration {

    @Bean
    fun createModelMapper(): ModelMapper {
        val modelMapper = ModelMapper()
        modelMapper.getConfiguration()
                .setSkipNullEnabled(true)
                .setFieldMatchingEnabled(true)
            //    .setMatchingStrategy(MatchingStrategies.LOOSE)
        //fileInputToFile(modelMapper)
        return modelMapper
    }


    private fun fileInputToFile(mapper: ModelMapper) {
        val p = PropertyMap<FileInput, File> {

        }

        mapper.addMappings(PropertyMap<FileInput?, File?>() {
                    fun configure() {
                        `when` { ctx -> ctx.getSource() != null }
                                .map(source) to File::javaClass
                    }
                })
    }

    // TODO
//    companion object {
//
//        private fun addStringLocalDateConverter(mapper: ModelMapper) {
//            mapper.addMappings(
//                    object : PropertyMap<StammdatenInputDTO?, Kunde?>() {
//                        protected fun configure() {
//                            `when` { ctx -> ctx.getSource() != null }.using(StringLocalDateConverter())
//                                    .map(source.getGeburtsDatum()).setGeburtsDatum(null)
//                            `when` { ctx -> ctx.getSource() != null }.using(StringLocalDateConverter())
//                                    .map(source.getGeburtsDatumPartner()).setGeburtsDatumPartner(null)
//                        }
//                    })
//        }
//
//        private fun addLocalDateStringConverter(mapper: ModelMapper) {
//            mapper.addMappings(
//                    object : PropertyMap<Kunde?, StammdatenOutputDTO?>() {
//                        protected fun configure() {
//                            `when` { ctx -> ctx.getSource() != null }.using(LocalDateStringConverter())
//                                    .map(source.getGeburtsDatum()).setGeburtsDatum(null)
//                            `when` { ctx -> ctx.getSource() != null }.using(LocalDateStringConverter())
//                                    .map(source.getGeburtsDatumPartner()).setGeburtsDatumPartner(null)
//                        }
//                    })
//        }
//
//        private fun addUuidStringConverter(mapper: ModelMapper) {
//            mapper.addMappings(
//                    object : PropertyMap<Kunde?, StammdatenOutputDTO?>() {
//                        protected fun configure() {
//                            `when` { ctx -> ctx.getSource() != null }.using(UuidStringConverter())
//                                    .map(source.getId()).setId(null)
//                        }
//                    })
//        }
//    }
}
