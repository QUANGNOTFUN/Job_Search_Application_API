package net.jobsearchapplication_api.security

import io.ktor.auth.*

data class UserIdPrincipalForUser(val id: Int): Principal