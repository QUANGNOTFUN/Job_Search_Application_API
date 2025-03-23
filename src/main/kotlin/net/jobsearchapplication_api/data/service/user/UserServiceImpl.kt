package net.jobsearchapplication_api.data.service.user

import net.jobsearchapplication_api.data.db.DatabaseFactory.dbQuery
import net.jobsearchapplication_api.data.db.extensions.toUser
import net.jobsearchapplication_api.data.db.schemas.UserTable
import net.jobsearchapplication_api.data.models.User
import net.jobsearchapplication_api.routes.user.UpdateInfoUserParams
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import java.time.DateTimeException
import java.time.Instant
import java.time.LocalDateTime
import java.util.*

class UserServiceImpl : UserService {
    override suspend fun getUser(id: UUID): User {
        val userRow = dbQuery { UserTable.select { UserTable.id eq id }.first() }
        return userRow.toUser()!!
    }

    override suspend fun updateInfoUser(id: UUID, params: UpdateInfoUserParams): Boolean {
        return transaction {
            UserTable.update({ UserTable.id eq id }) { row ->
                var isChanged = false

                params.fullName?.takeIf { it.isNotBlank() }?.let { row[fullName] = it; isChanged = true }
                params.phoneNumber?.takeIf { it.isNotBlank() }?.let { row[phone_number] = it; isChanged = true }
                params.avatar?.takeIf { it.isNotBlank() }?.let { row[avatar] = it; isChanged = true }
                params.bio?.takeIf { it.isNotBlank() }?.let { row[bio] = it; isChanged = true }
                params.gender.let { row[gender] = it; isChanged = true }
                params.location?.takeIf { it.isNotBlank() }?.let { row[location] = it; isChanged = true }
                params.cvUrl?.takeIf { it.isNotBlank() }?.let { row[cv_url] = it; isChanged = true }
                params.education?.takeIf { it.isNotBlank() }?.let { row[education] = it; isChanged = true }
                params.experience?.takeIf { it.isNotBlank() }?.let { row[experience] = it; isChanged = true }

                if (isChanged) row[updatedAt] = Instant.now()
            } > 0
        }

    }
}