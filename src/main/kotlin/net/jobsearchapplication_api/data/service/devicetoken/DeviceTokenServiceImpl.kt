package net.jobsearchapplication_api.data.service.devicetoken

import net.jobsearchapplication_api.data.db.DatabaseFactory.dbQuery
import net.jobsearchapplication_api.data.db.schemas.DeviceTokenTable
import net.jobsearchapplication_api.data.models.DeviceToken
import net.jobsearchapplication_api.routes.devicetoken.DeviceTokenParams
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.update

class DeviceTokenServiceImpl : DeviceTokenService {
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

	override suspend fun createToken(id: String, params: DeviceTokenParams): DeviceToken? {
		return dbQuery {
			val existingToken = DeviceTokenTable.select { DeviceTokenTable.id eq id }.firstOrNull()
			if (existingToken != null) {
				DeviceTokenTable.update({ DeviceTokenTable.id eq id }) {
					it[token] = params.token
				}
			} else {
				DeviceTokenTable.insert {
					it[DeviceTokenTable.id] = id
					it[token] = params.token
				}
			}
			DeviceToken(id, params.token) // ✅ trả lại object đúng kiểu
		}
	}

}