package net.jobSearchApplication_api.data.service.story

import net.jobSearchApplication_api.data.db.DatabaseFactory
import net.jobSearchApplication_api.data.db.extensions.toComment
import net.jobSearchApplication_api.data.db.extensions.toStory
import net.jobSearchApplication_api.data.db.extensions.toStoryJoinedWithUser
import net.jobSearchApplication_api.data.db.schemas.CommentTable
import net.jobSearchApplication_api.data.db.schemas.LikeTable
import net.jobSearchApplication_api.data.db.schemas.StoryTable
import net.jobSearchApplication_api.data.db.schemas.UserTable
import net.jobSearchApplication_api.data.models.Comment
import net.jobSearchApplication_api.data.models.Story
import net.jobSearchApplication_api.data.models.common.PaginatedResult
import net.jobSearchApplication_api.routes.story.StoryParams
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.statements.InsertStatement

class StoryServiceImpl : StoryService {

    override suspend fun get(id: Int): Story? {
        val storyRow = DatabaseFactory.dbQuery { StoryTable.select { StoryTable.id eq id }.first() }
        return storyRow.toStory()
    }

    override suspend fun getMyStories(userId: String, page: Int, limit: Int, isDraft: Boolean): PaginatedResult<Story> {
        var pageCount: Long = 0
        var nextPage: Long? = null

        val stories = DatabaseFactory.dbQuery {
            val query = StoryTable
                .innerJoin(UserTable, { UserTable.id }, { StoryTable.userId })
                .select { (StoryTable.userId eq userId) and (StoryTable.isDraft eq isDraft) }
                .orderBy(StoryTable.createdAt, SortOrder.DESC)

            val totalStories = query.count()
            pageCount = (totalStories + limit - 1) / limit
            nextPage = if (page < pageCount - 1) page + 1L else null

            query.limit(limit, (limit * page).toLong())
                .mapNotNull { it.toStoryJoinedWithUser() }
        }
        return PaginatedResult(pageCount, nextPage, stories)
    }

    override suspend fun getAllStories(page: Int, limit: Int): PaginatedResult<Story> {
        var pageCount: Long = 0
        var nextPage: Long? = null

        val stories = DatabaseFactory.dbQuery {
            val query = StoryTable
                .innerJoin(UserTable, { UserTable.id }, { StoryTable.userId })
                .selectAll()
                .orderBy(StoryTable.createdAt, SortOrder.DESC)

            val totalStories = query.count()
            pageCount = (totalStories + limit - 1) / limit
            nextPage = if (page < pageCount - 1) page + 1L else null

            query.limit(limit, (limit * page).toLong())
                .mapNotNull { it.toStoryJoinedWithUser() }
        }
        return PaginatedResult(pageCount, nextPage, stories)
    }

    override suspend fun getLikedStories(userId: String): List<Story> {
        return DatabaseFactory.dbQuery {
            val storyTable = StoryTable.alias("s")
            LikeTable.innerJoin(storyTable, { LikeTable.storyId }, { storyTable[StoryTable.id] })
                .select { LikeTable.userId eq userId }
                .mapNotNull { it.toStory() }
        }
    }

    override suspend fun add(storyParams: StoryParams): Story? {
        var statement: InsertStatement<Number>? = null
        DatabaseFactory.dbQuery {
            statement = StoryTable.insert {
                it[userId] = storyParams.userId
                it[title] = storyParams.title
                it[content] = storyParams.content
                it[isDraft] = storyParams.isDraft
            }
        }
        return statement?.resultedValues?.get(0).toStory()
    }

    override suspend fun update(id: Int, storyParams: StoryParams): Boolean {
        val result = DatabaseFactory.dbQuery {
            StoryTable.update({ StoryTable.id eq id }) {
                if (storyParams.userId != null) it[userId] = storyParams.userId
                if (storyParams.title != null) it[title] = storyParams.title
                if (storyParams.content != null) it[content] = storyParams.content
                if (storyParams.isDraft != null) it[isDraft] = storyParams.isDraft
            }
        }
        return result > 0 // Trả về true nếu có bản ghi được cập nhật
    }

    override suspend fun delete(storyId: Int): Boolean {
        var result = -1
        DatabaseFactory.dbQuery {
            result = StoryTable.deleteWhere { StoryTable.id eq storyId }
        }
        return result == 1
    }

    override suspend fun like(userId: String, storyId: Int): Boolean {
        var statement: InsertStatement<Number>? = null
        DatabaseFactory.dbQuery {
            statement = LikeTable.insert {
                it[this.userId] = userId
                it[this.storyId] = storyId
            }
        }
        return statement?.resultedValues?.isNotEmpty() ?: false
    }

    override suspend fun comment(userId: String, storyId: Int, comment: String): Boolean {
        var statement: InsertStatement<Number>? = null
        DatabaseFactory.dbQuery {
            statement = CommentTable.insert {
                it[this.userId] = userId
                it[this.storyId] = storyId
                it[this.comment] = comment
            }
        }
        return statement?.resultedValues?.isNotEmpty() ?: false
    }

    override suspend fun getComments(storyId: Int): List<Comment> {
        return DatabaseFactory.dbQuery {
            CommentTable.select { CommentTable.storyId eq storyId }.mapNotNull {
                it.toComment()
            }
        }
    }
}