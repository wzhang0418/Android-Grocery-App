package com.apolis.groceryapplication1.models

data class RegisterResponse(
    val data: User,
    val error: Boolean,
    val message: String
)

//From Post Login (success)
data class LoginSuccess(
    val token: String,
    val user: User
)
data class User(
    val __v: Int? = null,
    val _id: String? = null,
    val createdAt: String? = null,
    val email: String? = null,
    val firstName: String? = null,
    val mobile: String? = null,
    val password: String? = null
)

//From Post Login (fail)
data class LoginFail(
    val error: Boolean,
    val message: String
)