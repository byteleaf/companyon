package de.byteleaf.companyon.user.dto.input

import de.byteleaf.companyon.fileupload.dto.input.FileMetaInput

data class UserInput(var firstName: String? = null,
                     var lastName: String? = null,
                     var signature: FileMetaInput? = null,
                     var avatar: FileMetaInput? = null
)
