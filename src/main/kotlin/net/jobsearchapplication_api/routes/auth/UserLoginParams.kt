package net.jobsearchapplication_api.routes.auth

import java.util.UUID

data class UserLoginParams(
    val email: String,
    val password: String
)

data class LoginWithGoogleParams(
    val uuid: String
)