package net.jobsearchapplication_api.config

import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.jackson.*
import net.jobsearchapplication_api.data.db.DatabaseFactory
import net.jobsearchapplication_api.di.RepositoryProvider
import net.jobsearchapplication_api.routes.auth.authRoutes
import net.jobsearchapplication_api.routes.job.jobRoutes
import net.jobsearchapplication_api.routes.story.storyRoutes
import net.jobsearchapplication_api.routes.user.userRoutes

fun configureDatabase() {
    DatabaseFactory.init()
}

fun Application.configureContentNegotiation() {
    install(ContentNegotiation) {
        jackson {
            // Thêm support cho LocalDateTime
            registerModule(JavaTimeModule())
            // Disable write dates as timestamps
            disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            // Enable pretty printing
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }
}

fun Application.configureRouting(){
    authRoutes(RepositoryProvider.provideAuthRepository())
    userRoutes(RepositoryProvider.provideUserRepository())
    jobRoutes(RepositoryProvider.provideJobRepository())
}