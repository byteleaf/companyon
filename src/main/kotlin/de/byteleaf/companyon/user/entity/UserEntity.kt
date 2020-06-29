package de.byteleaf.companyon.user.entity

import org.springframework.data.annotation.Id;


class UserEntity(@Id var lateinit: Long) {

    var firstName: String? = null
    var lastName: String? = null
}