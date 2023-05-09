package com.ymlct.weatherapp.data.repository.remote.response

import java.io.IOException

/**
 * [Success] contain the body of the success state of the request.
 *
 * [ApiError] the non-2xx responses; contains the error body and the response status code.
 *
 * [NetworkError] network failure such as no internet connection cases.
 *
 * [UnknownError] unexpected exceptions occurred creating the request or processing the response.
 */

sealed class ApiResponse<out Result: Any, out ErrorBody: Any> {

    data class Success<out Result: Any>(val data: Result) : ApiResponse<Result, Nothing>()

    data class ApiError<out ErrorBody: Any>(val body: ErrorBody, val code: Int): ApiResponse<Nothing, ErrorBody>()

    data class NetworkError(val error: IOException): ApiResponse<Nothing, Nothing>()

    data class UnknownError(val error: String): ApiResponse<Nothing, Nothing>()

}