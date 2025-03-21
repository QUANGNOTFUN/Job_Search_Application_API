package net.jobsearchapplication_api.di

import net.jobsearchapplication_api.data.repository.auth.AuthRepository
import net.jobsearchapplication_api.data.repository.auth.AuthRepositoryImpl
import net.jobsearchapplication_api.data.repository.story.StoryRepository
import net.jobsearchapplication_api.data.repository.story.StoryRepositoryImpl
import net.jobsearchapplication_api.data.repository.user.UserRepository
import net.jobsearchapplication_api.data.repository.user.UserRepositoryImpl
import net.jobsearchapplication_api.data.service.auth.AuthServiceImpl
//import net.jobsearchapplication_api.data.service.story.StoryServiceImpl
import net.jobsearchapplication_api.data.service.user.UserServiceImpl

object RepositoryProvider {
    fun provideAuthRepository(): AuthRepository = AuthRepositoryImpl(AuthServiceImpl())
    fun provideUserRepository(): UserRepository = UserRepositoryImpl(UserServiceImpl())
//    fun provideStoryRepository(): StoryRepository = StoryRepositoryImpl(StoryServiceImpl())
}