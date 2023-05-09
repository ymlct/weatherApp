package com.ymlct.weatherapp.data.repository.remote.response

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Converter
import java.lang.reflect.Type

class CallToApiCallAdapter<Result : Any, ErrorBody : Any>(
    private val successType: Type,
    private val errorBodyConverter: Converter<ResponseBody, ErrorBody>
) : CallAdapter<Result, Call<ApiResponse<Result, ErrorBody>>> {

    override fun responseType(): Type = successType

    override fun adapt(call: Call<Result>): Call<ApiResponse<Result, ErrorBody>> {
        return ApiCall(call, errorBodyConverter)
    }
}