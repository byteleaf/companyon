package de.byteleaf.companyon.common.util

import java.lang.reflect.ParameterizedType
import kotlin.reflect.full.createInstance

object GenericSupportUtil {

    /**
     * To get the class of any generic from the [parent] Object
     * @param parent the parent class with the generics
     * @param genericPosition the position of the generic, the (default) is 0
     */
    fun getClassFromGeneric(parent: Any, genericPosition: Int = 0): Class<*> {
        return (parent.javaClass.genericSuperclass as ParameterizedType)
        .actualTypeArguments[genericPosition] as Class<*>
    }

    fun <T : Any> createInstanceFromGeneric(parent: Any, genericPosition: Int = 0): T {
        @Suppress("UNCHECKED_CAST")
        return (getClassFromGeneric(parent, genericPosition) as Class<T>).kotlin.createInstance()
    }
}