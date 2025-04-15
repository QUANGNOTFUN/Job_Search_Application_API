package net.jobSearchApplication_api.utils

import io.ktor.application.*
import io.ktor.auth.*
import net.jobSearchApplication_api.data.db.schemas.UserRole
import net.jobSearchApplication_api.security.UserIdPrincipalForUser

// Extension function cho ApplicationCall
// Mục đích: Lấy ID của user đã authenticate từ request hiện tại
fun ApplicationCall. getUserId(): String {
    return principal<UserIdPrincipalForUser>()?.id  // Lấy UserIdPrincipalForUser từ request
        ?: throw IllegalStateException("No authenticated user found")  // Nếu không tìm thấy, throw exception
}

fun ApplicationCall.getUserRole(): String {
    val principal = principal<UserIdPrincipalForUser>()
    return principal?.role ?: throw UnauthorizedException("User role not found")
}

fun ApplicationCall.isAdmin(): Boolean {
    return getUserRole() == UserRole.ADMIN.name
}

fun ApplicationCall.isRecruiter(): Boolean {
    return getUserRole() == UserRole.RECRUITER.name
}