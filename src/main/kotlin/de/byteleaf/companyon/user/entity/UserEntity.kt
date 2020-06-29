package de.byteleaf.companyon.user.entity

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "users")
data class UserEntity(
        var firstName: String? = null,
        var lastName: String? = null
) {
    @Id
    var id: Long = 0
}