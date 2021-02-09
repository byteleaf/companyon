package de.byteleaf.companyon.auth.configuration

import com.mongodb.client.MongoClient
import de.byteleaf.companyon.user.dto.User
import de.byteleaf.companyon.user.entity.UserEntity
import de.byteleaf.companyon.user.logic.UserService
import de.byteleaf.companyon.user.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.data.mongodb.MongoTransactionManager
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.transaction.TransactionStatus
import org.springframework.transaction.support.SimpleTransactionStatus


@Profile("non-sec")
@Configuration
@EnableWebSecurity
class NonSecConfiguration : WebSecurityConfigurerAdapter() {

    companion object {
        const val nonSecUserId = "602266b0aa3acc1a0fc4f349"
    }

    @Autowired
    private lateinit var mongoClient: MongoClient

    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var userRepository: UserRepository


    @Value("\${app.non-sec-user-oauth2-subject}")
    private lateinit var nonSecUserOAuth2Subject: String

    @Value("\${app.non-sec-user-admin}")
    private var nonSecUserAdmin: Boolean = false

    override fun configure(http: HttpSecurity) {
        val nonSecUser = createNonSecUser()
       val a = userService.findAll()
        http.cors().and().authorizeRequests()
                .antMatchers("/**").permitAll()
                .and().csrf { csrf -> csrf.disable() }
            .anonymous().principal(nonSecUser).authorities(nonSecUser.getRoles())

    }

    /**
     * Create the non sec user if it is still no existing
     */
    private fun createNonSecUser(): User {
        val nonSecUser = userService.findByOAuth2Subject(nonSecUserOAuth2Subject)
        return if (nonSecUser != null) nonSecUser else
        {
            val entity = UserEntity(nonSecUserOAuth2Subject, "Jeff", "Bytezos", "jeff@byteleaf.de", nonSecUserAdmin, null, null)
            entity.id = nonSecUserId
            userRepository.insert(entity)
            return userService.findByOAuth2Subject(nonSecUserOAuth2Subject)!!
        }
    }
}