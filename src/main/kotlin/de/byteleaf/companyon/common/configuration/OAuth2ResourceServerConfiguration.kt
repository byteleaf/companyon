package de.byteleaf.companyon.common.configuration

import de.byteleaf.companyon.common.auth.OAuth2JwtAuthenticationConverter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Profile("!non-sec & !test")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
class OAuth2ResourceServerConfiguration : WebSecurityConfigurerAdapter() {

    @Autowired
    private lateinit var jwtConverter: OAuth2JwtAuthenticationConverter

    override fun configure(http: HttpSecurity) {
        // TODO change graphql to /** ??
        http.authorizeRequests()
                .antMatchers("/graphql").authenticated()
                .and().oauth2ResourceServer().jwt()
                .jwtAuthenticationConverter(jwtConverter)
    }
}
