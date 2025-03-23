package net.jobsearchapplication_api.routes.user

import jakarta.validation.constraints.Size
import net.jobsearchapplication_api.data.db.schemas.Gender
import java.time.LocalDateTime

data class UpdateInfoUserParams(
    @field:Size(min = 3, max = 50, message = "Tên giới hạn từ 3 đến 50 kí tự")
    val fullName: String? = null,

    val phoneNumber: String? = null,

    val avatar: String? = null,

    @field:Size(max = 250, message = "Giới thiệu giới hạn từ 250 kí tự")
    val bio: String? = null,

    val gender: Gender,

    val location: String? = null,

    val cvUrl: String? = null,

    val education: String? = null,

    val experience: String? = null,
)