package de.byteleaf.companyon.project.boundary

//
//@GraphQLTest
//class ProjectQueryResolverTest : AbstractGraphQLTest() {
//    @Autowired
//    protected lateinit var graphQLTestTemplate: GraphQLTestTemplate
//
//    @Test
//    fun testGetProject() {
//        val project = ProjectEntity("Companyon", CompanyEntity("byteleaf"))
//        project.id = "1337"
//
//        doReturn(Optional.of(project)).`when`(projectRepository).findById(eq(project.id!!))
//
//        val variables = ObjectMapper().createObjectNode()
//        variables.put("id", "${project.id}")
//
//        val response = graphQLTestTemplate.perform("graphql/testGetProject.graphql", variables)
//
//        assertThat(response.isOk).isTrue()
//        assertThat(response.readTree().hasNonNull("errors"))
//                .describedAs("response has errors")
//                .isFalse()
//        assertThat(response.get("$.data.project.id")).isNotNull()
//        assertThat(response.get("$.data.project.name")).isEqualTo(project.name)
//    }
//}