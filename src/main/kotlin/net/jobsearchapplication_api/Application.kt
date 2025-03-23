package net.jobsearchapplication_api

import io.ktor.application.*
import io.ktor.server.tomcat.*
import net.jobsearchapplication_api.config.*
import net.jobsearchapplication_api.security.configureSecurity

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module() {
    configureDatabase()
    configureContentNegotiation()
    configureStatusPages()
    configureSecurity()
    configureRouting()
    configureValidation()
}
