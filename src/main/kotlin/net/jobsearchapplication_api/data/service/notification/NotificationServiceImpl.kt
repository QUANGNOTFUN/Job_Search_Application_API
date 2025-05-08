package net.jobsearchapplication_api.data.service.notification

import net.jobsearchapplication_api.data.db.schemas.NotificationTable
import net.jobsearchapplication_api.routes.notification.NotificationParams
import net.jobsearchapplication_api.data.db.DatabaseFactory.dbQuery
import net.jobsearchapplication_api.data.db.extensions.toNotification
import net.jobsearchapplication_api.data.models.Notification
import org.jetbrains.exposed.sql.*
import java.time.LocalDateTime

class NotificationServiceImpl : NotificationService {
	override suspend fun getAllNotificationByUserId(page: Int, limit: Int, userId: String): List<Notification> {
		return dbQuery {
			NotificationTable
				.select { NotificationTable.userId eq userId }
				.orderBy(NotificationTable.createdAt, SortOrder.DESC)
				.limit(limit, offset = (page - 1) * limit.toLong())
				.map { row ->
					Notification(
						id = row[NotificationTable.id],
						userId = row[NotificationTable.userId],
						senderId = row[NotificationTable.senderId],
						senderName = row[NotificationTable.senderName],
						title = row[NotificationTable.title],
						description = row[NotificationTable.description],
						type = row[NotificationTable.type],
						imageRes = row[NotificationTable.imageRes],
						relateId = row[NotificationTable.relatedId],
						isRead = row[NotificationTable.isRead],
						createAt = row[NotificationTable.createdAt]
					)
				}
		}
	}

	override suspend fun createNotification(params: NotificationParams): Notification {
		val statement = dbQuery {
			NotificationTable.insert {
				it[userId] = params.userId
				it[title] = params.title
				it[description] = params.description
				it[type] = params.type
				it[createdAt] = LocalDateTime.now()
				it[isRead] = false
			}
		}

		return statement.resultedValues?.firstOrNull()?.toNotification()
			?: throw IllegalStateException("Failed to create notification")
	}

	override suspend fun deleteNotification(id: Long): Boolean {
		return try {
			dbQuery {

				val deleteCount = NotificationTable.deleteWhere { NotificationTable.id eq id }
				deleteCount > 0
			}
		} catch (e: Exception) {
			println("Lỗi khi xóa thông báo với id $id: ${e.message}")
			false // Hoặc ném ngoại lệ tùy yêu cầu:
		}
	}

	override suspend fun updateNotification(id: Long, isRead: Boolean): Boolean {
		return dbQuery {
			try {
				val updatedRows = NotificationTable.update({ NotificationTable.id eq id }) {
					it[NotificationTable.isRead] = isRead
				}
				updatedRows > 0
			} catch (e: Exception) {
				throw Exception("Error updating notification: ${e.message}")
			}
		}
	}
}