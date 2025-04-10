package net.jobsearchapplication_api.data.db.schemas

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime

object MessageTable : Table("messages") {
    val id = uuid("id").autoGenerate()
    val senderId = uuid("sender_id").references(UserTable.id)
    val receiverId = uuid("receiver_id").references(UserTable.id)
    val message = text("description")
    val sentAt = datetime("sent_at")
    val isRead = bool("is_read").default(false)

    override val primaryKey = PrimaryKey(CompanyTable.id)

}