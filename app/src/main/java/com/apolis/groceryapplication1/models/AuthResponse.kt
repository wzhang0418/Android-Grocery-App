package com.apolis.groceryapplication1.models

data class RegisterResponse(
    val data: Data,
    val error: Boolean,
    val message: String
)
data class Data(
    val __v: Int,
    val _id: String,
    val createdAt: String,
    val email: String,
    val firstName: String,
    val mobile: String,
    val password: String
)

////////////////////////////////////////

//From Post Login (success)
data class LoginSuccess(
    val token: String,
    val user: User
)
data class User(
    val __v: Int,
    val _id: String,
    val createdAt: String,
    val email: String,
    val firstName: String,
    val mobile: String,
    val password: String
)

//From Post Login (fail)
data class LoginFail(
    val error: Boolean,
    val message: String
)