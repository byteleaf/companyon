package de.byteleaf.companyon.security.configuration

import de.byteleaf.companyon.user.control.UserService
import de.byteleaf.companyon.user.dto.input.UserInput
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.authority.SimpleGrantedAuthority

/**
 * Should only be used for unit testing and for the development
 */
@Profile("non-sec")
@Configuration
@EnableWebSecurity
class NonOAuth2ResourceServerConfiguration : WebSecurityConfigurerAdapter() {

    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var authenticationProvider: AuthenticationProvider

    override fun configure(http: HttpSecurity) {
        // will be used as current used if securtiy is disabled
        val nonSecUser = userService.create(UserInput("Jeff", "Bytezos", "jeff@byteleaf.de", true), "a")

        http.authorizeRequests().antMatchers("/**").permitAll()
                .and().csrf { csrf -> csrf.disable() }
            .anonymous().principal(nonSecUser).authorities(nonSecUser.getRoles())
    }

}
