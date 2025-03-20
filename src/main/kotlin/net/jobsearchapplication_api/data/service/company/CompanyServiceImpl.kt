package net.jobsearchapplication_api.data.service.company

import net.jobsearchapplication_api.data.db.schemas.CompanyTable
import net.jobsearchapplication_api.data.models.Company
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

//class CompanyServiceImpl : CompanyService {
//    override suspend fun getAllCompanies(page: Int): List<Company> {
//        return transaction {
//            CompanyTable
//                .selectAll()
//                .map { it.toCompany() }
//        }
//    }
//
//    // Implement các phương thức khác...
//}