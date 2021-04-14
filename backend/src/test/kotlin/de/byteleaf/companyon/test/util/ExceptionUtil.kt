package de.byteleaf.companyon.test.util

import de.byteleaf.companyon.auth.permission.PermissionException
import de.byteleaf.companyon.auth.permission.PermissionType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertThrows

class ExceptionUtil {
    companion object {
        fun expectPermissionException(permissionType: PermissionType, executable: () -> Unit) {
            val exception = assertThrows(PermissionException::class.java, executable)
            assertThat(exception.type).isEqualTo(permissionType)
        }
    }
}
