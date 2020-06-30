package de.byteleaf.companyon.user.dto.input

data class UserInput(var firstName: String? = null,
                     var lastName: String? = null,
                     var avatarUrl: String? = null,
                     var signature: Long? = null,
                     val avatar: Long? = null
)
