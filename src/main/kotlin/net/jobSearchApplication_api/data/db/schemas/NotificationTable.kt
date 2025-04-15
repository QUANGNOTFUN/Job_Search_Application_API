package net.jobSearchApplication_api.data.db.schemas

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object NotificationTable : Table("notifications") {
	val id = varchar("id",36)
	val userId = varchar("user_id", 36).references(UserTable.id).index() // Người nhận, thêm index
	val senderId = varchar("sender_id", 36).references(UserTable.id).nullable() // Người gửi, có thể null
	val senderName = varchar("sender_name", 100).references(UserTable.id).nullable() // Người gửi, có thể null
	val title = varchar("title", 100).nullable() // Bắt buộc có tiêu đề
	val description = text("description").nullable() // Mô tả có thể rỗng
	val type = varchar("type", 50).nullable()// Bắt buộc có loại
	val imageRes = integer("image_res").nullable() // Hình ảnh cho avatar
	val relatedId = uuid("related_id").nullable() // ID liên quan
	val isRead = bool("is_read").default(false).nullable()// Mặc định chưa đọc
	val createdAt = datetime("created_at").clientDefault { LocalDateTime.now() } // Thời gian tạo

	override val primaryKey = PrimaryKey(id)
}
