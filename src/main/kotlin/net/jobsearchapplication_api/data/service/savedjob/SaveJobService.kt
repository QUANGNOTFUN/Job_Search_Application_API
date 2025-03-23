//package net.jobsearchapplication_api.data.service.savedjob
//
//import net.jobsearchapplication_api.data.db.extensions.JobWithCompany
//import java.util.*
//
//interface SavedJobService {
//    suspend fun getSavedJobs(userId: UUID): List<JobWithCompany>
//    suspend fun saveJob(userId: UUID, jobId: UUID): Boolean
//    suspend fun unsaveJob(userId: UUID, jobId: UUID): Boolean
//    suspend fun isJobSaved(userId: UUID, jobId: UUID): Boolean
//    suspend fun getSavedJobCount(userId: UUID): Int
//}
//
