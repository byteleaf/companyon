
import de.byteleaf.companyon.security.control.PermissionEvaluatorProxy
import java.io.Serializable

import org.springframework.security.access.PermissionEvaluator

import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler

import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.access.method.MethodSecurityMetadataSource





@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
class MethodSecurityConfiguration : GlobalMethodSecurityConfiguration() {

    @Autowired
    private lateinit var permissionEvaluator: PermissionEvaluatorProxy

    @Autowired
    private lateinit var applicationContext: ApplicationContext

    @Override
    override fun createExpressionHandler(): MethodSecurityExpressionHandler {
        val expressionHandler = DefaultMethodSecurityExpressionHandler()
        expressionHandler.setPermissionEvaluator(permissionEvaluator)
        expressionHandler.setApplicationContext(applicationContext)
        return expressionHandler
    }

    override fun customMethodSecurityMetadataSource(): MethodSecurityMetadataSource? {
        return null
    }
}