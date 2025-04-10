package net.jobsearchapplication_api.data.repository.company

import net.jobsearchapplication_api.base.BaseResponse
import net.jobsearchapplication_api.routes.company.CompanyParams
import java.util.*

// CompanyRepository.kt
interface CompanyRepository {
    suspend fun getAllCompanies(page: Int, limit: Int): BaseResponse<Any>
    suspend fun getCompanyById(id: String): BaseResponse<Any>
    suspend fun createCompany(params: CompanyParams): BaseResponse<Any>
    suspend fun updateCompany(id: String,params: CompanyParams): BaseResponse<Any>
    suspend fun deleteCompany(id: String): BaseResponse<Any>
    suspend fun getCompanyJobs(id: String): BaseResponse<Any>
}

