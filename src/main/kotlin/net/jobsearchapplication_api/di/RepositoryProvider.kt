package net.jobsearchapplication_api.di

import net.jobsearchapplication_api.data.repository.auth.AuthRepository
import net.jobsearchapplication_api.data.repository.auth.AuthRepositoryImpl
import net.jobsearchapplication_api.data.repository.job.JobRepository
import net.jobsearchapplication_api.data.repository.job.JobRepositoryImpl
import net.jobsearchapplication_api.data.repository.company.CompanyRepository
import net.jobsearchapplication_api.data.repository.company.CompanyRepositoryImpl
import net.jobsearchapplication_api.data.repository.company.CompanyServiceImpl
import net.jobsearchapplication_api.data.repository.savejob.SavedJobRepository
import net.jobsearchapplication_api.data.repository.savejob.SavedJobRepositoryImpl
import net.jobsearchapplication_api.data.repository.user.UserRepository
import net.jobsearchapplication_api.data.repository.user.UserRepositoryImpl
import net.jobsearchapplication_api.data.service.auth.AuthServiceImpl
import net.jobsearchapplication_api.data.service.job.JobServiceImpl
import net.jobsearchapplication_api.data.service.user.UserServiceImpl
import net.jobsearchapplication_api.data.service.savedjob.SavedJobServiceImpl

object RepositoryProvider {
    //    fun provideStoryRepository(): StoryRepository = StoryRepositoryImpl(StoryServiceImpl())
    fun provideAuthRepository(): AuthRepository = AuthRepositoryImpl(AuthServiceImpl())
    fun provideUserRepository(): UserRepository = UserRepositoryImpl(UserServiceImpl())
    fun provideJobRepository(): JobRepository = JobRepositoryImpl(JobServiceImpl())
    fun provideCompanyRepository(): CompanyRepository = CompanyRepositoryImpl(CompanyServiceImpl())
    fun provideSavedJobRepository(): SavedJobRepository = SavedJobRepositoryImpl(SavedJobServiceImpl())
}