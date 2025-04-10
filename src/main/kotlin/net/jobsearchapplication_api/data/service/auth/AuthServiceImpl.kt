package net.jobsearchapplication_api.data.service.auth

import net.jobsearchapplication_api.data.db.DatabaseFactory.dbQuery
import net.jobsearchapplication_api.data.db.extensions.toUser
import net.jobsearchapplication_api.data.db.schemas.UserTable
import net.jobsearchapplication_api.data.models.User
import net.jobsearchapplication_api.routes.auth.CreateUserParams
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select

class AuthServiceImpl : AuthService {
//    override suspend fun registerUser(params: CreateUserParams): User? {
//        var statement: InsertStatement<Number>? = null
//        dbQuery {
//            statement = UserTable.insert {
//                it[email] = params.email
//                it[password_hash] = hash(params.password)
//                it[fullName] = params.fullName
//                it[avatar] = params.avatar
//                it[role] = params.role.uppercase()
//                it[createdAt] = LocalDateTime.now()
//                it[updatedAt] = LocalDateTime.now()
//            }
//        }
//        return statement?.resultedValues?.get(0).toUser()
//    }
//
//    override suspend fun loginUser(email: String, password: String): User? {
//        val hashedPassword = hash(password)
//        val userRow = dbQuery {
//            UserTable
//                .select {
//                    UserTable.email eq email and
//                    (UserTable.password_hash eq hashedPassword)
//                }
//                .firstOrNull()
//        }
//        return userRow.toUser()
//    }
//
    override suspend fun findUserByUUID(uuid: String): User? {
        return dbQuery {
            UserTable
                .select { UserTable.id.eq(uuid) }
                .map { it.toUser() }
                .singleOrNull()
        }
    }

    override suspend fun createUser(params: CreateUserParams): Boolean {
        return dbQuery {
            val result = UserTable.insert {
                it[id] = params.uuid
            }
            result.resultedValues?.isNotEmpty() == true
        }
    }
}