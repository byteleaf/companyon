package de.byteleaf.companyon.common.configuration

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
        //addKindInputDTOListConverter(modelMapper)
        return modelMapper
    }

//    private fun addKindInputDTOListConverter(mapper: ModelMapper) {
//            mapper.addMappings(
//                    object : PropertyMap<StammdatenInputDTO?, Kunde?>() {
//                        protected fun configure() {
//                            `when` { ctx -> ctx.getSource() != null }.using(KindInputDTOListConverter())
//                                    .map(source.getKinder()).setKinder(null)
//                        }
//                    })
//        }

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
