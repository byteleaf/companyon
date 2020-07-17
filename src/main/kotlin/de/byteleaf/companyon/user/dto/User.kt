package de.byteleaf.companyon.user.dto

import de.byteleaf.companyon.common.configuration.NoArgConstructor
import de.byteleaf.companyon.common.dto.BaseDTO
import de.byteleaf.companyon.fileupload.dto.FileMeta

@NoArgConstructor
data class User(val firstName: String,
                val lastName: String,
                var signature: FileMeta,
                var avatar: FileMeta
) : BaseDTO()
