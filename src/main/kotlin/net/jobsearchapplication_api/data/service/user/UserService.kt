package net.jobsearchapplication_api.data.service.user

import net.jobsearchapplication_api.data.models.User

interface UserService {
    suspend fun getUser(id: Int): User
}