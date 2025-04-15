package net.jobSearchApplication_api.data.db.schemas

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.date
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object UserTable : Table("users") {
    val id = varchar("id", 36)
    val fullName = varchar("full_name", 50).nullable()
    val phoneNumber = varchar("phone_number", 20).nullable()
    val avatar = text("avatar").nullable()
    val bio = varchar("bio", 250).nullable()
    val birthDay = date("birth_day").nullable()
    val gender = enumerationByName("gender", 10, Gender::class).nullable()
    val cvUrl = text("cv_url").nullable()
    val favoriteJobPosting = text("favorite_job_posting").nullable()
    val createdAt = datetime("created_at").default(LocalDateTime.now())
    val updatedAt = datetime("updated_at").default(LocalDateTime.now())
    val role = varchar("role", 20).default(UserRole.USER.name)
    override val primaryKey = PrimaryKey(id)
}

enum class Gender {
    Male, Female, Other
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