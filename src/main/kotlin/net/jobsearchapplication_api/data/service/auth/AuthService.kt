package net.jobsearchapplication_api.data.service.auth

import net.jobsearchapplication_api.data.models.User
import net.jobsearchapplication_api.routes.auth.CreateUserParams

interface AuthService {
    suspend fun registerUser(params: CreateUserParams): User?
    suspend fun loginUser(email: String, password: String): User?
    suspend fun findUserByEmail(email: String): User?
}