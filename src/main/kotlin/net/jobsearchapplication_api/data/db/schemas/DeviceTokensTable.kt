package net.jobsearchapplication_api.data.db.schemas

import net.jobsearchapplication_api.data.db.schemas.JobApplicationTable.references
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime

object DeviceTokenTable: Table("devicetoken"){
	val id = varchar("user_id", 36).references(UserTable.id)
	val token = varchar("token",250)
	override val primaryKey = PrimaryKey(id)
}