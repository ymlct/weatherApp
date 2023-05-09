package com.ymlct.weatherapp.data.repository.remote.response

import com.ymlct.weatherapp.domain.utils.log
import okhttp3.Request
import okhttp3.ResponseBody
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Converter
import retrofit2.Response
import java.io.IOException

/**
 * Extends default set of possible outcomes with additional ones,
 * defined as [ApiResponse] inheritors
 */
class ApiCall<Result: Any, ErrorBody: Any>(
    private val delegate: Call<Result>,
    private val errorConverter: Converter<ResponseBody, ErrorBody>
) : Call<ApiResponse<Result, ErrorBody>> {

    override fun enqueue(callback: Callback<ApiResponse<Result, ErrorBody>>) {
        log("ApiCall::enqueue(): enter")
        delegate.enqueue(object : Callback<Result> {

            override fun onResponse(call: Call<Result>, response: Response<Result>) {
                val body = response.body()
                val code = response.code()
                val error = response.errorBody()

                if (response.isSuccessful) {
                    if (body != null) {
                        log("ApiCall::onResponse(): response.isSuccessful, response=$response")
                        callback.onResponse(
                            this@ApiCall,
                            Response.success(ApiResponse.Success(body))
                        )
                    } else {
                        log("ApiCall::onResponse(): response.isSuccessful but body is null")
                        callback.onResponse(
                            this@ApiCall,
                            Response.success(ApiResponse.UnknownError("Response is successful but body is null."))
                        )
                    }
                } else {
                    val errorBody: ErrorBody? = when {
                        error == null -> {
                            log("ApiCall::onResponse(): response.isNotSuccessful, error is null")
                            null
                        }
                        error.contentLength() == 0L -> {
                            log("ApiCall::onResponse(): response.isNotSuccessful, error.contentLength=0")
                            null
                        }
                        else -> try {
                            log("ApiCall::onResponse(): response.isNotSuccessful, error=$error")
                            errorConverter.convert(error)
                        } catch (ex: Exception) {
                            log("ApiCall::onResponse(): response.isNotSuccessful, exception=$ex")
                            null
                        }
                    }
                    if (errorBody != null) {
                        callback.onResponse(
                            this@ApiCall,
                            Response.success(ApiResponse.ApiError(errorBody, code))
                        )
                    } else {
                        callback.onResponse(
                            this@ApiCall,
                            Response.success(ApiResponse.UnknownError("Response is not successful, error body is null."))
                        )
                    }
                }
            }

            override fun onFailure(call: Call<Result>, throwable: Throwable) {
                log("ApiCall::onFailure(): exception=$throwable")
                val networkResponse = when (throwable) {
                    is IOException -> ApiResponse.NetworkError(throwable)
                    else -> ApiResponse.UnknownError(
                        "Exception message: ${throwable.message}.\n" +
                                "Exception stack trace:${throwable.stackTraceToString()}"
                    )
                }
                callback.onResponse(this@ApiCall, Response.success(networkResponse))
            }
        })
    }

    override fun isExecuted() = delegate.isExecuted

    override fun clone() = ApiCall(delegate.clone(), errorConverter)

    override fun isCanceled() = delegate.isCanceled

    override fun cancel() = delegate.cancel()

    override fun execute(): Response<ApiResponse<Result, ErrorBody>> {
        throw UnsupportedOperationException("NetworkResponseCall doesn't support execute")
    }

    override fun request(): Request = delegate.request()

    override fun timeout(): Timeout = delegate.timeout()
}
