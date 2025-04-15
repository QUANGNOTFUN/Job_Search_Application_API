package net.jobSearchApplication_api.config

import com.fasterxml.jackson.core.JsonParseException
import com.fasterxml.jackson.databind.exc.MismatchedInputException
import com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.response.*
import net.jobSearchApplication_api.base.BaseResponse
import io.ktor.http.HttpStatusCode
import net.jobSearchApplication_api.utils.UnauthorizedException

fun Application.configureStatusPages() {
    install(StatusPages) {
        exception<MismatchedInputException> { cause ->
            val error = when (cause) {
                is MissingKotlinParameterException -> BaseResponse.ErrorResponse("Missing attribute `${cause.parameter.name}`")
                else -> BaseResponse.ErrorResponse(cause.message!!)
            }
            call.respond(error.statusCode, error)
        }
        exception<JsonParseException> { cause ->
            call.respond(BaseResponse.ErrorResponse(cause.message!!))
        }
        exception<UnauthorizedException> { cause ->
            call.respond(
                HttpStatusCode.Forbidden,
                BaseResponse.ErrorResponse(cause.message ?: PERMISSION_DENIED)
            )
        }
    }
}