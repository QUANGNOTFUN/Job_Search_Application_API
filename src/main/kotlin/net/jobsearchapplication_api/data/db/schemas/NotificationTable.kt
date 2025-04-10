package net.jobsearchapplication_api.data.db.schemas

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object NotificationTable : Table("notifications") {
	val id = uuid("id").autoGenerate()
    val userId = uuid("user_id").references(UserTable.id) // Liên kết với người nhận
    val title = varchar("title", 100).nullable() // Tiêu đề thông báo
    val description = text("description").nullable()// Nội dung thông báo
    val type = varchar("type", 50).nullable() // Loại thông báo (ví dụ: "new_message", "job_match")
    val relatedId = uuid("related_id").nullable() // ID liên quan (ví dụ: jobId, messageId)
    val isRead = bool("is_read").default(false).nullable()// Trạng thái đã đọc
    val createdAt = datetime("created_at").clientDefault { LocalDateTime.now() } // Thời gian tạo

    override val primaryKey = PrimaryKey(id)
}