package net.jobsearchapplication_api.data.db.schemas

import net.jobsearchapplication_api.data.db.schemas.UserTable
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object NotificationTable : Table("notifications") {
	val id = long("id").autoIncrement()
	val userId = varchar("user_id", 36).references(UserTable.id).index() // Người nhận, thêm index
	val senderId = varchar("sender_id", 36).references(UserTable.id).nullable() // Người gửi, có thể null
	val senderName = varchar("sender_name", 100).nullable() // Người gửi, có thể null
	val title = varchar("title", 100) // Bắt buộc có tiêu đề
	val description = text("description").nullable() // Mô tả có thể rỗng
	val type = varchar("type", 50)// Bắt buộc có loại
	val imageRes = integer("image_res").nullable() // Hình ảnh cho avatar
	val relatedId = varchar("related_id",36).nullable() // ID liên quan
	val isRead = bool("is_read").default(false).nullable()// Mặc định chưa đọc
	val createdAt = datetime("created_at").clientDefault { LocalDateTime.now() } // Thời gian tạo

	override val primaryKey = PrimaryKey(id)
}