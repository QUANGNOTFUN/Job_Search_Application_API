package net.jobsearchapplication_api.config

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.jackson.*
import net.jobsearchapplication_api.data.db.DatabaseFactory
import net.jobsearchapplication_api.di.RepositoryProvider
import net.jobsearchapplication_api.routes.auth.authRoutes
import net.jobsearchapplication_api.routes.story.storyRoutes
import net.jobsearchapplication_api.routes.user.userRoutes

fun configureDatabase() {
    DatabaseFactory.init()
}

fun Application.configureContentNegotiation() {
    install(ContentNegotiation) {
        jackson()
    }
}

fun Application.configureRouting(){
    authRoutes(RepositoryProvider.provideAuthRepository())
    userRoutes(RepositoryProvider.provideUserRepository())
//    storyRoutes(RepositoryProvider.provideStoryRepository())
}