package de.byteleaf.companyon.user.boundary

//
//@GraphQLTest
//class UserQueryResolverTest : AbstractGraphQLTest() {
//    @Autowired
//    protected lateinit var graphQLTestTemplate: GraphQLTestTemplate
//
//    @Test
//    fun testGetCurrentUser() {
//        val signature = FileMeta("", "", "")
//        val user = UserEntity(
//                "oauth2Sub",
//                "Joseph",
//                "Bytezos",
//                "joseph@bytezos.de",
//                signature,
//                null
//        )
//        user.id = "1234"
//
//        doReturn(Optional.of(user)).`when`(userRepository).findById(eq(user.id!!))
//
//        val variables = ObjectMapper().createObjectNode()
//        variables.put("id", "${user.id}")
//
//        val response = graphQLTestTemplate.perform("graphql/testGetUser.graphql", variables)
//
//        Assertions.assertThat(response.isOk).isTrue()
//        Assertions.assertThat(response.readTree().hasNonNull("errors"))
//                .describedAs("response has errors")
//                .isFalse()
//        Assertions.assertThat(response.get("$.data.user.id")).isNotNull()
//        Assertions.assertThat(response.get("$.data.user.firstName")).isEqualTo(user.firstName)
//        Assertions.assertThat(response.get("$.data.user.lastName")).isEqualTo(user.lastName)
//    }
//}