//package net.jobsearchapplication_api.data.service.job
//
//import net.jobsearchapplication_api.data.models.Job
//import java.util.*
//
//class JobServiceImpl : JobService {
//    override suspend fun getAllJobs(page: Int, limit: Int): List<Job> {
//        return transaction {
//            JobTable
//                .select { JobTable.status eq JobStatus.ACTIVE.name }
//                .orderBy(JobTable.createdAt, SortOrder.DESC)
//                .limit(limit, offset = ((page - 1) * limit).toLong())
//                .map { it.toJob() }
//        }
//    }
//
//    override suspend fun getJobById(id: UUID): Job? {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun createJob(params: JobParams): Job? {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun updateJob(id: UUID, params: JobParams): Boolean {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun deleteJob(id: UUID): Boolean {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun searchJobs(query: String, location: String?, type: String?, page: Int): List<Job> {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun getJobsByCompany(companyId: UUID): List<Job> {
//        TODO("Not yet implemented")
//    }
//
//    // Implement các phương thức khác...
//}