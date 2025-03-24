package net.jobsearchapplication_api.data.db.schemas

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.CurrentTimestamp
import org.jetbrains.exposed.sql.javatime.datetime
import org.jetbrains.exposed.sql.javatime.timestamp
import java.time.LocalDateTime

object UserTable : Table("users") {
    val id = uuid("id").autoGenerate()
    val fullName = varchar("full_name", 50)
    val email = varchar("email", 100)
    val password_hash = text("password_hash")
    val phone_number = varchar("phone_number", 20).nullable()
    val avatar = text("avatar").nullable()
    val bio = varchar("bio", 250).nullable()
    val gender = enumerationByName("gender", 10, Gender::class).nullable()
    val location = varchar("location", 255).nullable()
    val cv_url = text("cv_url").nullable()
    val education = text("education").nullable()
    val experience = text("experience").nullable()
    val createdAt = datetime("created_at").default(LocalDateTime.now())
    val updatedAt = datetime("updated_at").default(LocalDateTime.now())
    val role = varchar("role", 20).default(UserRole.USER.name)

    override val primaryKey = PrimaryKey(id)
}

enum class Gender {
    Male, FeMale, Other
}
enum class UserRole {
    USER,
    RECRUITER,
    ADMIN;

    companion object {
        fun isValid(role: String): Boolean {
            return values().any { it.name == role.uppercase() }
        }
    }
}