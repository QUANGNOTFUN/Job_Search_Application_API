package net.jobsearchapplication_api.data.repository.auth

import io.ktor.http.*
import net.jobsearchapplication_api.base.BaseResponse
import net.jobsearchapplication_api.config.*
import net.jobsearchapplication_api.data.service.auth.AuthService
import net.jobsearchapplication_api.routes.auth.CreateUserParams
import net.jobsearchapplication_api.routes.auth.LoginWithGoogleParams
import net.jobsearchapplication_api.routes.auth.UserLoginParams
import net.jobsearchapplication_api.security.JwtConfig
import net.jobsearchapplication_api.security.TokenResponse

class AuthRepositoryImpl(
    private val authService: AuthService
) : AuthRepository {
//    override suspend fun registerUser(params: CreateUserParams): BaseResponse<Any> {
//        return if (isEmailExist(params.email)) {
//            BaseResponse.ErrorResponse(message = MESSAGE_EMAIL_ALREADY_REGISTERED)
//        } else {
//            val user = authService.registerUser(params)
//            if (user != null) {
//                val token = JwtConfig.instance.createAccessToken(
//                    id = user.id,
//                    name = user.fullName,
//                    email = user.email,
//                    role = user.role
//                )
//
//                BaseResponse.SuccessResponse(
//                    data = TokenResponse(
//                        accessToken = token,
//                        userId = user.id.toString(),
//                        name = user.fullName,
//                        email = user.email,
//                        role = user.role
//                    ),
//                    message = USER_REGISTRATION_SUCCESS
//                )
//            } else {
//                BaseResponse.ErrorResponse(GENERIC_ERROR)
//            }
//        }
//    }
//
//    override suspend fun loginUser(params: UserLoginParams): BaseResponse<Any> {
//        val user = authService.loginUser(params.email, params.password)
//        return if (user != null) {
//            val token = JwtConfig.instance.createAccessToken(
//                id = user.id,
//                name = user.fullName,
//                email = user.email,
//                role = user.role
//            )
//
//            BaseResponse.SuccessResponse(
//                data = TokenResponse(
//                    accessToken = token,
//                    userId = user.id.toString(),
//                    name = user.fullName,
//                    email = user.email,
//                    role = user.role
//                ),
//                message = USER_LOGIN_SUCCESS
//            )
//        } else {
//            BaseResponse.ErrorResponse(USER_LOGIN_FAILURE, HttpStatusCode.Unauthorized)
//        }
//    }

    override suspend fun loginWithGoogle(params: LoginWithGoogleParams): BaseResponse<Any> {
        return if (authService.loginWithGoogle(params)) {
            BaseResponse.SuccessResponse(
                data = null,
                message = USER_LOGIN_SUCCESS
            )
        } else {
            BaseResponse.ErrorResponse(USER_LOGIN_FAILURE, HttpStatusCode.Unauthorized)
        }

    }

//    private suspend fun isEmailExist(email: String): Boolean {
//        return authService.findUserByEmail(email) != null
//    }
}