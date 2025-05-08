package net.jobsearchapplication_api.di

import net.jobsearchapplication_api.data.repository.notification.NotificationRepositoryImpl
import net.jobsearchapplication_api.data.service.notification.NotificationServiceImpl
import net.jobsearchapplication_api.data.repository.auth.AuthRepository
import net.jobsearchapplication_api.data.repository.auth.AuthRepositoryImpl
import net.jobsearchapplication_api.data.repository.job.JobRepository
import net.jobsearchapplication_api.data.repository.job.JobRepositoryImpl
import net.jobsearchapplication_api.data.repository.company.CompanyRepository
import net.jobsearchapplication_api.data.repository.company.CompanyRepositoryImpl
import net.jobsearchapplication_api.data.repository.devicetoken.DeviceTokenRepositoryImpl
import net.jobsearchapplication_api.data.service.company.CompanyServiceImpl
import net.jobsearchapplication_api.data.repository.jobcategory.JobCategoryRepositoryImpl
import net.jobsearchapplication_api.data.repository.user.UserRepository
import net.jobsearchapplication_api.data.repository.user.UserRepositoryImpl
import net.jobsearchapplication_api.data.service.auth.AuthServiceImpl
import net.jobsearchapplication_api.data.service.job.JobServiceImpl
import net.jobsearchapplication_api.data.service.jobcategory.JobCategoryServiceImpl
import net.jobsearchapplication_api.data.service.user.UserServiceImpl
import net.jobsearchapplication_api.data.repository.jobapplication.JobApplicationRepositoryImpl
import net.jobsearchapplication_api.data.service.devicetoken.DeviceTokenServiceImpl
import net.jobsearchapplication_api.data.service.jobapplication.JobApplicationServiceImpl


object RepositoryProvider {
    //    fun provideStoryRepository(): StoryRepository = StoryRepositoryImpl(StoryServiceImpl())
    fun provideAuthRepository(): AuthRepository = AuthRepositoryImpl(AuthServiceImpl())
    fun provideUserRepository(): UserRepository = UserRepositoryImpl(UserServiceImpl())
    fun provideJobRepository(): JobRepository = JobRepositoryImpl(JobServiceImpl())
    fun provideCompanyRepository(): CompanyRepository = CompanyRepositoryImpl(CompanyServiceImpl())
    fun provideNotificationRepository(): NotificationRepositoryImpl = NotificationRepositoryImpl(NotificationServiceImpl())
    fun provideJobCategoryRepository(): JobCategoryRepositoryImpl = JobCategoryRepositoryImpl(JobCategoryServiceImpl())
    fun provideJobApplicationRepository(): JobApplicationRepositoryImpl = JobApplicationRepositoryImpl(JobApplicationServiceImpl())
	fun provideDeviceTokenRepository(): DeviceTokenRepositoryImpl = DeviceTokenRepositoryImpl(DeviceTokenServiceImpl())

}