package de.byteleaf.companyon.auth.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
class MethodSecurityConfiguration : GlobalMethodSecurityConfiguration() {

//    @Autowired
//    private lateinit var permissionEvaluatorProxy: PermissionEvaluatorProxy
//
//    @Autowired
//    private lateinit var applicationContext: ApplicationContext
//
//    override fun createExpressionHandler(): MethodSecurityExpressionHandler {
//        val expressionHandler = DefaultMethodSecurityExpressionHandler()
//        expressionHandler.setPermissionEvaluator(permissionEvaluatorProxy)
//        expressionHandler.setApplicationContext(applicationContext)
//        return expressionHandler
//    }
}