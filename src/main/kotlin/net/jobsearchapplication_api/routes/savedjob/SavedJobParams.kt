package net.jobsearchapplication_api.routes.savedjob

import java.util.*

data class SavedJobParams(
    val userId: UUID,
    val jobId: UUID
)