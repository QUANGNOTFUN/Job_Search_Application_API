package net.jobsearchapplication_api.data.repository.company

import net.jobsearchapplication_api.base.BaseResponse
import net.jobsearchapplication_api.routes.company.CompanyParams
import java.util.*

// CompanyRepository.kt
interface CompanyRepository {
    suspend fun getAllCompanies(page: Int): BaseResponse<Any>
    suspend fun getCompanyById(id: UUID): BaseResponse<Any>
    suspend fun createCompany(params: CompanyParams): BaseResponse<Any>
    suspend fun updateCompany(id: UUID, params: CompanyParams): BaseResponse<Any>
    suspend fun getCompanyJobs(id: UUID): BaseResponse<Any>
}

