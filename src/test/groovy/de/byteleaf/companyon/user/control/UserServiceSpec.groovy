package de.byteleaf.companyon.user.control

import de.byteleaf.companyon.user.dto.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@ContextConfiguration(classes = [UserService.class])
class UserServiceSpec extends Specification {

    @Autowired
    private UserService userService

    def 'getCurrentUser'() {
        expect:
        userService.currentUser == result
        where:
        result << new User(99, "Jeff", "Bytezos")
    }
}
