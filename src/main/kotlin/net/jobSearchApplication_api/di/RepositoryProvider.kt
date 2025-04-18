package net.jobSearchApplication_api.di

import net.jobSearchApplication_api.data.repository.auth.AuthRepository
import net.jobSearchApplication_api.data.repository.auth.AuthRepositoryImpl
import net.jobSearchApplication_api.data.repository.job.JobRepository
import net.jobSearchApplication_api.data.repository.job.JobRepositoryImpl
import net.jobSearchApplication_api.data.repository.company.CompanyRepository
import net.jobSearchApplication_api.data.repository.company.CompanyRepositoryImpl
import net.jobSearchApplication_api.data.repository.company.CompanyServiceImpl
import net.jobSearchApplication_api.data.repository.favorite.FavoriteRepository
import net.jobSearchApplication_api.data.repository.favorite.FavoriteRepositoryImpl
import net.jobSearchApplication_api.data.repository.jobcategory.JobCategoryRepositoryImpl
import net.jobSearchApplication_api.data.repository.user.UserRepository
import net.jobSearchApplication_api.data.repository.user.UserRepositoryImpl
import net.jobSearchApplication_api.data.service.auth.AuthServiceImpl
import net.jobSearchApplication_api.data.service.favorite.FavoriteServiceImpl
import net.jobSearchApplication_api.data.service.job.JobServiceImpl
import net.jobSearchApplication_api.data.service.jobcategory.JobCategoryServiceImpl
import net.jobSearchApplication_api.data.service.user.UserServiceImpl
import net.jobsearchapplication_api.data.repository.notification.NotificationRepositoryImpl
import net.jobSearchapplication_api.data.service.jobapplication.JobApplicationServiceImpl
import net.jobSearchapplication_api.data.repository.jobapplication.JobApplicationRepositoryImpl
import net.jobSearchapplication_api.data.service.notification.NotificationServiceImpl


object RepositoryProvider {
    //    fun provideStoryRepository(): StoryRepository = StoryRepositoryImpl(StoryServiceImpl())
    fun provideAuthRepository(): AuthRepository = AuthRepositoryImpl(AuthServiceImpl())
    fun provideUserRepository(): UserRepository = UserRepositoryImpl(UserServiceImpl())
    fun provideJobRepository(): JobRepository = JobRepositoryImpl(JobServiceImpl())
    fun provideCompanyRepository(): CompanyRepository = CompanyRepositoryImpl(CompanyServiceImpl())
    fun provideFavoriteJobPostingRepository(): FavoriteRepository = FavoriteRepositoryImpl(FavoriteServiceImpl())
    fun provideNotificationRepository(): NotificationRepositoryImpl = NotificationRepositoryImpl(NotificationServiceImpl())
    fun provideJobCategoryRepository(): JobCategoryRepositoryImpl = JobCategoryRepositoryImpl(JobCategoryServiceImpl())
    fun provideJobApplicationRepository(): JobApplicationRepositoryImpl = JobApplicationRepositoryImpl(
        JobApplicationServiceImpl()
    )

}