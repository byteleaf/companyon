package de.byteleaf.companyon.auth.configuration

import de.byteleaf.companyon.auth.permission.PermissionEvaluatorProxy
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Configuration
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
class MethodSecurityConfiguration : GlobalMethodSecurityConfiguration() {

    @Autowired
    private lateinit var permissionEvaluatorProxy: PermissionEvaluatorProxy

    @Autowired
    private lateinit var applicationContext: ApplicationContext

    override fun createExpressionHandler(): MethodSecurityExpressionHandler {
        val expressionHandler = DefaultMethodSecurityExpressionHandler()
        expressionHandler.setPermissionEvaluator(permissionEvaluatorProxy)
        expressionHandler.setApplicationContext(applicationContext)
        return expressionHandler
    }
}