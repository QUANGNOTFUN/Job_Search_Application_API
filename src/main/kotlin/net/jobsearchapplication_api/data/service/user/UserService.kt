package net.jobsearchapplication_api.data.service.user

import net.jobsearchapplication_api.data.models.User
import java.util.*

interface UserService {
    suspend fun getUser(id: UUID): User
}