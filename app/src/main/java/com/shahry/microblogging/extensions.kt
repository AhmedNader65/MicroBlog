package com.shahry.microblogging

import com.shahry.microblogging.model.Resource
import retrofit2.Response


suspend fun <T> getResponse(
    request: suspend () -> Response<T>,
    defaultErrorMessage: String
): Resource<T> {
    return try {
        val result = request.invoke()
        if (result.isSuccessful) {
            return Resource.success(result.body())
        } else {
            Resource.error(result.message(), null)
        }
    } catch (e: Throwable) {
        e.printStackTrace()
        Resource.error("Unknown Error", null)
    }
}
