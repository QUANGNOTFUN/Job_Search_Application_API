package net.jobSearchApplication_api

import io.ktor.application.*
import io.ktor.server.tomcat.*
import net.jobSearchApplication_api.config.configureContentNegotiation
import net.jobSearchApplication_api.config.configureDatabase
import net.jobSearchApplication_api.config.configureRouting
import net.jobSearchApplication_api.config.configureStatusPages
import net.jobSearchApplication_api.security.configureSecurity

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module() {
    configureDatabase()
    configureContentNegotiation()
    configureStatusPages()
    configureSecurity()
    configureRouting()
}
