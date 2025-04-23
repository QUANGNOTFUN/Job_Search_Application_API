package net.jobsearchapplication_api.data.service.auth

import net.jobsearchapplication_api.data.db.DatabaseFactory.dbQuery
import net.jobsearchapplication_api.data.db.extensions.toUser
import net.jobsearchapplication_api.data.db.schemas.Gender
import net.jobsearchapplication_api.data.db.schemas.UserTable
import net.jobsearchapplication_api.data.models.User
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select

class AuthServiceImpl : AuthService {
    override suspend fun findUserByUUID(uuid: String): User? {
        return dbQuery {
            UserTable
                .select { UserTable.id.eq(uuid) }
                .map { it.toUser() }
                .singleOrNull()
        }
    }

    override suspend fun createUser(uuid: String): Boolean {
        return dbQuery {
            val result = UserTable.insert {
                it[id] = uuid
                it[gender] = Gender.Other
            }
            result.resultedValues?.isNotEmpty() == true
        }
    }
}