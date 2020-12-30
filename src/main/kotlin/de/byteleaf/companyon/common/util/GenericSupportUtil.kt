package de.byteleaf.companyon.common.util

import de.byteleaf.companyon.common.control.AbstractDataService
import org.springframework.stereotype.Service
import java.lang.reflect.ParameterizedType
import kotlin.reflect.full.createInstance

object GenericSupportUtil {

    /**
     * To get the class of any generic from the [parent] Object
     * @param parent the parent class with the generics
     * @param genericPosition the position of the generic, the (default) is 0
     */
    fun getClassFromGeneric(parent: Any, genericPosition: Int): Class<*> {
        return (parent.javaClass.genericSuperclass as ParameterizedType)
        .actualTypeArguments[genericPosition] as Class<*>
    }

    fun <T : Any> createInstanceFromGeneric(parent: Any, genericPosition: Int): T {
        @Suppress("UNCHECKED_CAST")
        return (getClassFromGeneric(parent, genericPosition) as Class<T>).kotlin.createInstance()
    }
}