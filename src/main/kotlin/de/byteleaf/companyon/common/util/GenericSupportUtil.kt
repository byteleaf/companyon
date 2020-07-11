package de.byteleaf.companyon.common.util

import de.byteleaf.companyon.common.control.AbstractDataService
import org.springframework.stereotype.Service
import java.lang.reflect.ParameterizedType

object GenericSupportUtil {

    /**
     * To get the class of any generic from the [parentJavaCls] Object
     * @param parentJavaCls the parent java class with the generics
     * @param genericPosition the position of the generic, the (default) is 0
     */
    fun getClassFromGeneric(parent: Any, genericPosition: Int = 0): Class<*> {
        return (parent.javaClass.genericSuperclass as ParameterizedType)
        .actualTypeArguments[genericPosition] as Class<*>
    }
}