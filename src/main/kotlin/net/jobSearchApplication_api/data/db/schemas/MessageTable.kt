package net.jobSearchApplication_api.data.db.schemas

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime

object MessageTable : Table("messages") {
    val id = uuid("id").autoGenerate()
    val senderId = varchar("user_id", 36).references(UserTable.id)
    val receiverId = varchar("user_id", 36).references(UserTable.id)
    val message = text("message")
    val sentAt = datetime("sent_at")
    val isRead = bool("is_read").default(false)

    override val primaryKey = PrimaryKey(CompanyTable.id)

}