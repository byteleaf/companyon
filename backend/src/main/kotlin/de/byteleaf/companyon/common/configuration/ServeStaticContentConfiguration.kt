package de.byteleaf.companyon.common.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.core.io.Resource
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.resource.PathResourceResolver

/**
 * Configures a fallback so we can do the routing on the client-side.
 */
@Configuration
class ServeStaticContentConfiguration : WebMvcConfigurer {
    override fun addViewControllers(registry: ViewControllerRegistry) {
        registry.addViewController("/")
                .setViewName("forward:/index.html")
    }

    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("/**")
                // path to frontend must always come last, otherwise we will override files from e.g. GraphQL playground
                .addResourceLocations("classpath:/static/", "classpath:/public/", "classpath:/frontend/")
                .resourceChain(true)
                .addResolver(object : PathResourceResolver() {
                    override fun getResource(resourcePath: String, location: Resource): Resource? {
                        val requestedResource = location.createRelative(resourcePath)

                        /*
                         * the routing is done on the client side, if the resource doesn't exist,
                         * let the client handle everything
                         */
                        if (requestedResource.exists() && requestedResource.isReadable) {
                            return requestedResource
                        }

                        /*
                         * make sure we only fallback if the file actually exists,
                         * thus all resource locations are checked first,
                         * and only in the end we serve our frontend code/fallback file
                         */
                        val fallbackResource = location.createRelative("/index.html")

                        if (fallbackResource.exists() && fallbackResource.isReadable) {
                            return fallbackResource
                        }

                        return null
                    }
                })
    }
}