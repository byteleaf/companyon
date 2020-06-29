package de.byteleaf.companyon.user.dto

data class User(val id: Long, var firstName: String, var lastName: String) {
    var avatarUrl: String? = null
}