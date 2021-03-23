package de.byteleaf.companyon.auth.configuration

import de.byteleaf.companyon.user.dto.User
import de.byteleaf.companyon.user.entity.UserEntity
import de.byteleaf.companyon.user.logic.UserService
import de.byteleaf.companyon.user.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Profile("non-sec")
@Configuration
@EnableWebSecurity
class NonSecConfiguration : WebSecurityConfigurerAdapter() {

    companion object {
        const val NON_SEC_USER_ID = "602266b0aa3acc1a0fc4f349"
        const val NON_SEC_FIRST_NAME = "Jeff"
        const val NON_SEC_LAST_NAME = "Bytezos"
        const val NON_SEC_EMAIL = "jeff@byteleaf.de"
        const val NON_SEC_OAUTH_SUBJECT = "non-sec-user-oauth2-subject"
    }

    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var userRepository: UserRepository

    @Value("\${app.non-sec-user-admin}")
    private var nonSecUserAdmin: Boolean = false


    override fun configure(http: HttpSecurity) {
        val nonSecUser = createAndPersistNonSecUser()
        http.cors().and().authorizeRequests()
            .antMatchers("/**").permitAll()
            .and().csrf { csrf -> csrf.disable() }
            .anonymous().principal(nonSecUser).authorities(nonSecUser.getRoles())
    }

    /**
     * Create the non sec user and persists it, if it is still no existing
     */
    fun createAndPersistNonSecUser(): User {
        val nonSecUser = userService.findByOAuth2Subject(NON_SEC_OAUTH_SUBJECT)
        return if (nonSecUser != null) nonSecUser else {
            val entity = UserEntity(NON_SEC_OAUTH_SUBJECT, NON_SEC_FIRST_NAME, NON_SEC_LAST_NAME, NON_SEC_EMAIL, nonSecUserAdmin, null, null)
            entity.id = NON_SEC_USER_ID
            userRepository.insert(entity)
            return userService.findByOAuth2Subject(NON_SEC_OAUTH_SUBJECT)!!
        }
    }
}