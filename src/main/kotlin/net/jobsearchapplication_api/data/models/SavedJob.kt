package net.jobsearchapplication_api.data.models

import java.time.LocalDateTime
import java.util.UUID

data class SavedJob(
    val id: UUID,
    val userId: UUID,
    val jobId: UUID,
    val savedAt: LocalDateTime = LocalDateTime.now()
)