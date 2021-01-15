package de.byteleaf.companyon.auth.configuration

import de.byteleaf.companyon.auth.control.PermissionEvaluatorProxy
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Configuration
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration
import org.springframework.security.access.intercept.RunAsManagerImpl

import org.springframework.security.access.intercept.RunAsManager




@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
class MethodSecurityConfiguration : GlobalMethodSecurityConfiguration() {
//
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
//
//    override fun runAsManager(): RunAsManager? {
//        val runAsManager = RunAsManagerImpl()
//        runAsManager.key = "MyRunAsKey"
//        return runAsManager
//    }

}