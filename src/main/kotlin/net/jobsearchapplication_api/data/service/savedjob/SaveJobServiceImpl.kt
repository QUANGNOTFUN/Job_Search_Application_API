//package net.jobsearchapplication_api.data.service.savedjob
//
//import net.jobsearchapplication_api.data.db.extensions.JobWithCompany
//import java.util.*

//class SavedJobServiceImpl : SavedJobService {
//    override suspend fun getSavedJobs(userId: UUID): List<JobWithCompany> {
//        return transaction {
//            (SavedJobTable
//                .join(JobTable, { jobId }, { JobTable.id })
//                .join(CompanyTable, { JobTable.companyId }, { CompanyTable.id }))
//                .select { SavedJobTable.userId eq userId }
//                .map { it.toJobWithCompany() }
//        }
//    }
//
//    // Implement các phương thức khác...
//}