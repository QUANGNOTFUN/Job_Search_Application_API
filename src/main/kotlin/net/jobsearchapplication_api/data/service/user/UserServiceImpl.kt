package net.jobsearchapplication_api.data.service.user

import net.jobsearchapplication_api.data.db.DatabaseFactory.dbQuery
import net.jobsearchapplication_api.data.db.extensions.toUser
import net.jobsearchapplication_api.data.db.schemas.UserTable
import net.jobsearchapplication_api.data.models.User
import org.jetbrains.exposed.sql.select

class UserServiceImpl : UserService {
    override suspend fun getUser(id: Int): User {
        val userRow = dbQuery { UserTable.select { UserTable.id eq id }.first() }
        return userRow.toUser()!!
    }
}