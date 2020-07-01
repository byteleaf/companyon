package de.byteleaf.companyon.user.dto

import de.byteleaf.companyon.fileupload.dto.File

data class User(var id: String? = null,
                var firstName: String? = null,
                var lastName: String? = null,
                var signature: File? = null,
                val avatar: File? = null
)