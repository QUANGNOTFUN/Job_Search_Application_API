package net.jobsearchapplication_api.config

import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.jackson.*
import net.jobsearchapplication_api.routes.notification.notificationRoutes
import net.jobsearchapplication_api.data.db.DatabaseFactory
import net.jobsearchapplication_api.di.RepositoryProvider
import net.jobsearchapplication_api.routes.auth.authRoutes
import net.jobsearchapplication_api.routes.company.companyRoutes
import net.jobsearchapplication_api.routes.job.jobRoutes
import net.jobsearchapplication_api.routes.jobcategory.jobCategoryRoutes
import net.jobsearchapplication_api.routes.user.userRoutes
import net.jobsearchapplication_api.routes.jobapplication.jobApplicationRoutes
import net.jobsearchapplication_api.routes.notification.notificationRoutes

fun configureDatabase() {
    DatabaseFactory.init()
}
fun Application.configureFirebase() {
	try {
		// Đọc tệp từ resources
		val serviceAccount = this::class.java.classLoader.getResourceAsStream("firebase-adminsdk.json")
			?: throw IllegalStateException("firebase-adminsdk.json not found in resources")

		val options = FirebaseOptions.builder()
			.setCredentials(GoogleCredentials.fromStream(serviceAccount))
			.setDatabaseUrl("https://job-search-application-462dd.firebaseio.com")
			.build()
		FirebaseApp.initializeApp(options)
		println("Firebase Admin SDK initialized")
	} catch (e: Exception) {
		println("Error initializing Firebase Admin SDK: ${e.message}")
	}
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
	notificationRoutes(RepositoryProvider.provideNotificationRepository())
    jobCategoryRoutes(RepositoryProvider.provideJobCategoryRepository())
    jobApplicationRoutes(RepositoryProvider.provideJobApplicationRepository())
}