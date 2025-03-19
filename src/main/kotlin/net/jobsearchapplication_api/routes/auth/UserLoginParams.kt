package net.jobsearchapplication_api.routes.auth

data class UserLoginParams(
    val email: String,
    val password: String
)