package net.jobsearchapplication_api.data.service.devicetoken

import net.jobsearchapplication_api.data.db.DatabaseFactory
import net.jobsearchapplication_api.data.db.DatabaseFactory.dbQuery
import net.jobsearchapplication_api.data.db.extensions.toDeviceTokens
import net.jobsearchapplication_api.data.db.extensions.toJob
import net.jobsearchapplication_api.data.db.schemas.CompanyTable
import net.jobsearchapplication_api.data.db.schemas.DeviceTokenTable
import net.jobsearchapplication_api.data.db.schemas.JobTable
import net.jobsearchapplication_api.data.models.DeviceToken
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.statements.InsertStatement

class DeviceTokenServiceImpl: DeviceTokenService {
	override suspend fun getToken(id: String): DeviceToken? {
		return dbQuery {
			DeviceTokenTable.select { DeviceTokenTable.id eq id }.firstOrNull()?.let {
				DeviceToken(
					id = it[DeviceTokenTable.id],
					token = it[DeviceTokenTable.token]
				)
			}
		}
	}

	override suspend fun createToken(id: String, params:String): DeviceToken? {
		var statement: InsertStatement<Number>? = null
		DatabaseFactory.dbQuery {
			statement = DeviceTokenTable.insert {
				it[DeviceTokenTable.id] = id
				it[DeviceTokenTable.token] = params
			}
		}
		return statement?.resultedValues?.get(0)?.toDeviceTokens()
	}
}