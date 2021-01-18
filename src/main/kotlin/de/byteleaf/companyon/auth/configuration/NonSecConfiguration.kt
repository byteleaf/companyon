package de.byteleaf.companyon.auth.configuration

import de.byteleaf.companyon.user.control.UserService
import de.byteleaf.companyon.user.dto.input.UserInput
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

    @Autowired
    private lateinit var userService: UserService

    @Value("\${app.non-sec-user-oauth2-subject}")
    private lateinit var nonSecUserOAuth2Subject: String

    @Value("\${app.non-sec-user-admin}")
    private var nonSecUserAdmin: Boolean = false

    override fun configure(http: HttpSecurity) {
        val nonSecUser = userService.create(UserInput("Jeff", "Bytezos", "jeff@byteleaf.de", nonSecUserAdmin), nonSecUserOAuth2Subject)
        http.cors().and().authorizeRequests()
                .antMatchers("/**").permitAll()
                .and().csrf { csrf -> csrf.disable() }
            .anonymous().principal(nonSecUser).authorities(nonSecUser.getRoles())

    }
}