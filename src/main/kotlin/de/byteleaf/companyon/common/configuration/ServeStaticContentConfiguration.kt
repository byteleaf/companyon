package de.byteleaf.companyon.common.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.core.io.Resource
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.resource.PathResourceResolver

@Configuration
class ServeStaticContentConfiguration : WebMvcConfigurer {
    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/public/")
                .resourceChain(true)
                .addResolver(object : PathResourceResolver() {
                    override fun getResource(resourcePath: String, location: Resource): Resource {
                        val requestedResource = location.createRelative(resourcePath)

                        // the routing is done on the client side, if the resource doesn't exist,
                        // let the client handle everything
                        return if (requestedResource.exists() && requestedResource.isReadable) {
                            requestedResource
                        } else location.createRelative("/index.html")
                    }
                })
    }
}