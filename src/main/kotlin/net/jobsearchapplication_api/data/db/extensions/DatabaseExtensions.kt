package net.jobsearchapplication_api.data.db.extensions

import net.jobsearchapplication_api.data.db.schemas.CommentTable
import net.jobsearchapplication_api.data.db.schemas.StoryTable
import net.jobsearchapplication_api.data.db.schemas.UserTable
import net.jobsearchapplication_api.data.models.Comment
import net.jobsearchapplication_api.data.models.Job
import net.jobsearchapplication_api.data.models.Story
import net.jobsearchapplication_api.data.models.User
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow?.toUser(): User? {
    return if (this == null) null
    else User(
        id = this[UserTable.id],
        fullName = this[UserTable.fullName],
        avatar = this[UserTable.avatar],
        email = this[UserTable.email],
        createdAt = this[UserTable.createdAt].toString(),
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
            avatar = this[UserTable.avatar],
            email = this[UserTable.email],
            createdAt = this[UserTable.createdAt].toString()
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

fun ResultRow?.toJob(): Job?{

}