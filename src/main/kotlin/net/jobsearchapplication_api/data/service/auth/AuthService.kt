package net.jobsearchapplication_api.data.service.auth

import net.jobsearchapplication_api.data.models.User

interface AuthService {
//    suspend fun registerUser(params: CreateUserParams): User?
//    suspend fun loginUser(email: String, password: String): User?
    suspend fun findUserByUUID(uuid: String): User?
    suspend fun createUser(uuid: String): Boolean
}