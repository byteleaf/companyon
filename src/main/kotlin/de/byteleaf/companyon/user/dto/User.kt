package de.byteleaf.companyon.user.dto

import de.byteleaf.companyon.common.dto.BaseDTO
import de.byteleaf.companyon.fileupload.dto.FileMeta

data class User(var firstName: String? = null,
                var lastName: String? = null,
                var signature: FileMeta? = null,
                var avatar: FileMeta? = null
) : BaseDTO()