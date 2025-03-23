package net.jobsearchapplication_api.data.db.extensions

import net.jobsearchapplication_api.data.db.schemas.CommentTable
import net.jobsearchapplication_api.data.db.schemas.StoryTable
import net.jobsearchapplication_api.data.db.schemas.UserTable
import net.jobsearchapplication_api.data.db.schemas.UserTable.bio
import net.jobsearchapplication_api.data.models.Comment
import net.jobsearchapplication_api.data.models.Story
import net.jobsearchapplication_api.data.models.User
import org.jetbrains.exposed.dao.withHook
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.ZoneId

fun ResultRow?.toUser(): User? {
    return if (this == null) null
    else User(
        id = this[UserTable.id],
        fullName = this[UserTable.fullName],
        email = this[UserTable.email],
        passwordHash = this[UserTable.password_hash],
        phoneNumber = this[UserTable.phone_number],
        avatar = this[UserTable.avatar],
        bio = this[UserTable.bio],
        gender = this[UserTable.gender],
        location = this[UserTable.location],
        cvUrl = this[UserTable.cv_url],
        education = this[UserTable.education],
        experience = this[UserTable.experience],
        createdAt = this[UserTable.createdAt].atZone(ZoneId.systemDefault()).toLocalDateTime(),
        updatedAt = this[UserTable.updatedAt].atZone(ZoneId.systemDefault()).toLocalDateTime()
    )
}

fun ResultRow?.toStory(): Story? {
    return if (this == null) null
    else Story(
        id = this[StoryTable.id],
        title = this[StoryTable.title],
        content = this[StoryTable.content],
        isDraft = this[StoryTable.isDraft],
        createdAt = this[StoryTable.createdAt].toString()
    )
}

fun ResultRow?.toStoryJoinedWithUser(): Story? {
    return if (this == null) null
    else Story(
        id = this[StoryTable.id],
        user = User(
            id = this[UserTable.id],
            fullName = this[UserTable.fullName],
            email = this[UserTable.email],
            passwordHash = this[UserTable.password_hash],
            phoneNumber = this[UserTable.phone_number],
            avatar = this[UserTable.avatar],
            bio = this[UserTable.bio],
            gender = this[UserTable.gender],
            location = this[UserTable.location],
            cvUrl = this[UserTable.cv_url],
            education = this[UserTable.education],
            experience = this[UserTable.experience],
            createdAt = this[UserTable.createdAt].atZone(ZoneId.systemDefault()).toLocalDateTime(),
            updatedAt = this[UserTable.updatedAt].atZone(ZoneId.systemDefault()).toLocalDateTime()
        ),
        title = this[StoryTable.title],
        content = this[StoryTable.content],
        isDraft = this[StoryTable.isDraft],
        createdAt = this[StoryTable.createdAt].toString()
    )
}

fun ResultRow?.toComment(): Comment? {
    return if (this == null) null
    else Comment(
        id = this[CommentTable.id],
        userId = this[CommentTable.userId],
        storyId = this[CommentTable.storyId],
        comment = this[CommentTable.comment],
        createdAt = this[CommentTable.createdAt].toString()
    )
}

fun addNewColumn() {
    transaction {
        SchemaUtils.createMissingTablesAndColumns(UserTable)
    }
}