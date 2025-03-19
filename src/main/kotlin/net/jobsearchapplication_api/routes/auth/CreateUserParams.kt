package net.jobsearchapplication_api.routes.auth

data class CreateUserParams(
    val fullName: String,
    val email: String,
    val password: String,
    val avatar: String
)
