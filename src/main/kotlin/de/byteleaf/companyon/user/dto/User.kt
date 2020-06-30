package de.byteleaf.companyon.user.dto

data class User(var id: Long? = null,
                var firstName: String? = null,
                var lastName: String? = null,
                var avatarUrl: String? = null,
                var signature: Long? = null,
                val avatar: Long? = null
)