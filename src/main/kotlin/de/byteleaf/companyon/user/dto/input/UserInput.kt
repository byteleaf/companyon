package de.byteleaf.companyon.user.dto.input

import de.byteleaf.companyon.fileupload.dto.input.FileInput

data class UserInput(var firstName: String? = null,
                     var lastName: String? = null,
                     var signature: FileInput? = null,
                     val avatar: FileInput? = null
)
