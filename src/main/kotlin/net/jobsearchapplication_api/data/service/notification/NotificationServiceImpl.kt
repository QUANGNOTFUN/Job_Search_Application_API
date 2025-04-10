package net.jobsearchapplication_api.data.service.notification

import net.jobsearchapplication_api.data.db.DatabaseFactory.dbQuery
import net.jobsearchapplication_api.data.db.extensions.toNotification
import net.jobsearchapplication_api.data.db.schemas.NotificationTable
import net.jobsearchapplication_api.data.db.schemas.NotificationTable.id
import net.jobsearchapplication_api.data.models.Notification
import net.jobsearchapplication_api.routes.notification.NotificationParams
import org.jetbrains.exposed.sql.*
import java.time.LocalDateTime
import java.util.*

class NotificationServiceImpl :NotificationService {
	override suspend fun getAllNotifications(page: Int, limit: Int): List<Notification> {
		return dbQuery {
			NotificationTable
				.selectAll()
				.orderBy(NotificationTable.createdAt, SortOrder.DESC) // Sắp xếp theo thời gian tạo, mới nhất trước
				.limit(limit, offset = (page - 1) * limit.toLong()) // Phân trang: offset = (trang - 1) * giới hạn
				.map { row ->
					Notification(
						id = row[NotificationTable.id],
						userId = row[NotificationTable.userId],
						title = row[NotificationTable.title],
						message = row[NotificationTable.description],
						relateId = row[NotificationTable.relatedId],
						createAt = row[NotificationTable.createdAt]
					)
				}
		}
	}

	override suspend fun getNotificationById(Id: UUID): Notification? {
		return dbQuery {
			NotificationTable.select { NotificationTable.id eq id }.firstOrNull()?.let {
				Notification(
					id = it[NotificationTable.id],
					userId = it[NotificationTable.userId],
					title = it[NotificationTable.title],
					message = it[NotificationTable.description],
					type = it[NotificationTable.type],
					relateId = it[NotificationTable.relatedId],
					createAt = it[NotificationTable.createdAt]
				)
			}
		}
	}

	override suspend fun createNotification(params: NotificationParams): Notification? {
		val statement = dbQuery {
			NotificationTable.insert {
				it[id] = UUID.randomUUID()
				it[userId] = params.userId
				it[title] = params.title
				it[description] = params.message
				it[type] = params.type
//				it[relatedId] = params.relateId
				it[createdAt] = LocalDateTime.now()
			}
		}

		return statement.resultedValues?.firstOrNull()?.toNotification()
			?: throw IllegalStateException("Failed to create company")
	}

	override suspend fun updateNotification(id: UUID, params: NotificationParams): Notification? {
		val updated = dbQuery {
			NotificationTable.update({ NotificationTable.id eq id }) {
				if (params.title != null) it[title] = params.title
				if (params.message != null) it[NotificationTable.description] = params.message
				if (params.type != null) it[type] = params.type
//				if (params.relateId != null) it[relatedId] = params.relateId
			}
		}

		if (updated > 0) {
			return dbQuery {
				NotificationTable.select { NotificationTable.id eq id }
					.map { it.toNotification() }
					.firstOrNull()
			}
		}

		throw IllegalStateException("Failed to update notification")
	}

	override suspend fun deleteNotification(id: UUID): Boolean {
		return try {
			dbQuery {
				val deleteCount = NotificationTable.deleteWhere { NotificationTable.id eq id }
				deleteCount > 0
			}
		} catch (e: Exception) {
			false // Hoặc ném ngoại lệ tùy yêu cầu
		}
	}
}