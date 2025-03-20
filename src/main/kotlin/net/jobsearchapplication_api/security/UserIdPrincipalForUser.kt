package net.jobsearchapplication_api.security

import io.ktor.auth.*
import java.util.*

data class UserIdPrincipalForUser(val id: UUID): Principal