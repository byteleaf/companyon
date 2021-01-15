package de.byteleaf.companyon.auth.configuration

import de.byteleaf.companyon.auth.oauth.OAuth2JwtAuthenticationConverter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter


@Profile("!non-sec")
@Configuration
@EnableWebSecurity
class OAuth2ResourceServerConfiguration : WebSecurityConfigurerAdapter() {

    @Autowired
    private lateinit var jwtConverter: OAuth2JwtAuthenticationConverter

    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
            .antMatchers("/graphql").authenticated()
            .and().oauth2ResourceServer().jwt()
            .jwtAuthenticationConverter(jwtConverter)
    }
}
