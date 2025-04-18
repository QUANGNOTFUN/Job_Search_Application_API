package net.jobSearchapplication_api.data.service.notification

import net.jobSearchApplication_api.data.db.DatabaseFactory.dbQuery
import net.jobSearchApplication_api.data.db.extensions.toNotification
import net.jobSearchApplication_api.data.db.schemas.NotificationTable
import net.jobSearchApplication_api.data.models.Notification
import net.jobSearchApplication_api.data.service.notification.NotificationService
import net.jobSearchapplication_api.routes.notification.NotificationParams
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
						createdAt = row[NotificationTable.createdAt]
					)
				}
		}
	}

	override suspend fun createNotification(params: NotificationParams): Notification {
		if (params.title.isBlank() || params.type.isBlank()) {
			throw IllegalArgumentException("Title and type are required")
		}
		val statement = dbQuery {
			NotificationTable.insert {
				it[userId] = params.userId
				it[title] = params.title
				it[description] = params.description
				it[type] = params.type
				it[createdAt] = LocalDateTime.now()
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

	override suspend fun updateNotification(id: Long): Notification? {
		return dbQuery {
			// Cập nhật cột isRead trong bảng
			val updatedRows = NotificationTable.update({ NotificationTable.id eq id }) {
				it[NotificationTable.isRead] = isRead
			}

			// Nếu có bản ghi được cập nhật, truy vấn lại để trả về Notification
			if (updatedRows > 0) {
				NotificationTable
					.select { NotificationTable.id eq id }
					.mapNotNull { row ->
						Notification(
							id = row[NotificationTable.id],
							userId = row[NotificationTable.userId],
							title = row[NotificationTable.title],
							description = row[NotificationTable.description],
							type = row[NotificationTable.type],
							imageRes = row[NotificationTable.imageRes],
							createdAt = row[NotificationTable.createdAt],
							senderId = row[NotificationTable.senderId],
							senderName = row[NotificationTable.senderName],
							isRead = row[NotificationTable.isRead],
							relateId = row[NotificationTable.relatedId]
						)
					}
					.singleOrNull()
			} else {
				null
			}
		}
	}
}