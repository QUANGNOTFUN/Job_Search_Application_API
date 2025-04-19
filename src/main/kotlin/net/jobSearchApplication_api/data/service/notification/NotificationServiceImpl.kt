package net.jobSearchApplication_api.data.service.notification

import net.jobSearchApplication_api.data.db.DatabaseFactory.dbQuery
import net.jobSearchApplication_api.data.db.extensions.toNotification
import net.jobSearchApplication_api.data.db.schemas.NotificationTable
import net.jobSearchApplication_api.data.models.Notification
import net.jobSearchApplication_api.routes.notification.NotificationParams
import org.jetbrains.exposed.sql.SortOrder
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import java.time.LocalDateTime

class NotificationServiceImpl : NotificationService {
	override suspend fun getAllNotificationByUserId(page: Int, limit: Int, id: String): List<Notification> {
		return dbQuery {
			NotificationTable
				.select { NotificationTable.userId eq id }
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

	override suspend fun createNotification(params: NotificationParams): Notification? {
		val statement = dbQuery {
			NotificationTable.insert {
				it[id] = params.id
				it[userId] = params.userId
				it[title] = params.title
				it[description] = params.description
				it[type] = params.type
//				it[relatedId] = params.relateId
				it[createdAt] = LocalDateTime.now()
			}
		}

		return statement.resultedValues?.firstOrNull()?.toNotification()
			?: throw IllegalStateException("Failed to create company")
	}

	override suspend fun deleteNotification(id: String): Boolean {
		return try {
			dbQuery {
				// Giả sử NotificationTable.id là String. Nếu là UUID, cần chuyển đổi:
				// val uuid = UUID.fromString(id)
				val deleteCount = NotificationTable.deleteWhere { NotificationTable.id eq id }
				deleteCount > 0
			}
		} catch (e: Exception) {
			// Log lỗi để hỗ trợ debug
			println("Lỗi khi xóa thông báo với id $id: ${e.message}")
			false // Hoặc ném ngoại lệ tùy yêu cầu:
			// throw IllegalStateException("Không thể xóa thông báo: ${e.message}", e)
		}
	}
}