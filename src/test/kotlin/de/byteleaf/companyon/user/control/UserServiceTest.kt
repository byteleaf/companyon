package de.byteleaf.companyon.user.control

import de.byteleaf.companyon.common.WithMockCustomUser
import de.byteleaf.companyon.fileupload.dto.input.FileMetaInput
import de.byteleaf.companyon.user.dto.input.UserInput
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import org.springframework.test.context.ActiveProfiles

//@DataMongoTest
//@ActiveProfiles("test")
//class UserServiceTest {
//
//    @Autowired
//    private lateinit var userService: UserService
//
//    @Test
//    @WithMockCustomUser("oauth2Sub", "Joseph", "Bytezos")
//    fun getCurrentUser() {
//        val emptyFileMeta = FileMetaInput("", "", "")
//        val newUser = UserInput(
//                "oauth2Sub",
//                "Joseph",
//                "Bytezos",
//                "joseph@bytezos.de",
//                emptyFileMeta,
//                emptyFileMeta
//        )
//
//        userService.create(newUser)
//
//        val currentUser = userService.getCurrentUser()
//
//        assertEquals(newUser.firstName, currentUser.firstName)
//        assertEquals(newUser.lastName, currentUser.lastName)
//    }
//}