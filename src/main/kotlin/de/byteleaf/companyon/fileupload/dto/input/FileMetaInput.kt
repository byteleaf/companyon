package de.byteleaf.companyon.fileupload.dto.input

import de.byteleaf.companyon.common.configuration.NoArgConstructor

@NoArgConstructor
data class FileMetaInput(
        val id: String,
        val url: String,
        val mimeType: String)
