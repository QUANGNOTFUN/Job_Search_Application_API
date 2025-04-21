package net.jobsearchapplication_api.security

import io.ktor.auth.*

data class UserIdPrincipalForUser(
    val id: String,
    val name: String,
    val email: String,
    val role: String
) : Principal