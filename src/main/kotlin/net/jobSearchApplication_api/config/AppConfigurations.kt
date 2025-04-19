package net.jobSearchApplication_api.config

import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.jackson.*
import net.jobSearchApplication_api.data.db.DatabaseFactory
import net.jobSearchApplication_api.di.RepositoryProvider
import net.jobSearchApplication_api.routes.auth.authRoutes
import net.jobSearchApplication_api.routes.company.companyRoutes
import net.jobSearchApplication_api.routes.job.jobRoutes
import net.jobSearchApplication_api.routes.jobcategory.jobCategoryRoutes
import net.jobSearchApplication_api.routes.favorite.favoritePostsRoutes
import net.jobSearchApplication_api.routes.user.userRoutes
import net.jobSearchapplication_api.routes.jobapplication.jobApplicationRoutes
import net.jobSearchapplication_api.routes.notification.notificationRoutes

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
//    storyRoutes(RepositoryProvider.provideStoryRepository())
    jobRoutes(RepositoryProvider.provideJobRepository())
    companyRoutes(RepositoryProvider.provideCompanyRepository())
    favoritePostsRoutes(RepositoryProvider.provideFavoriteJobPostingRepository())
	notificationRoutes(RepositoryProvider.provideNotificationRepository())
    jobCategoryRoutes(RepositoryProvider.provideJobCategoryRepository())
    jobApplicationRoutes(RepositoryProvider.provideJobApplicationRepository())
}