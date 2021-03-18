package de.byteleaf.companyon.user.dto

import de.byteleaf.companyon.common.annotation.NoArgConstructor
import de.byteleaf.companyon.common.dto.BaseDTO
import de.byteleaf.companyon.fileupload.dto.FileMeta
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

@NoArgConstructor
data class User(
    override val id: String,
    val oauth2Subject: String?,
    val firstName: String,
    val lastName: String,
    val email: String,
    val admin: Boolean = false,
    val signature: FileMeta?,
    val avatar: FileMeta?
) : BaseDTO() {

    fun getRoles(): List<GrantedAuthority> =
        if (admin) listOf(SimpleGrantedAuthority("ROLE_ADMIN")) else listOf(SimpleGrantedAuthority("ROLE_USER"))
}
