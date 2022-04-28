package com.shahry.microblogging.data.api

import com.shahry.microblogging.model.Author
import retrofit2.Response
import retrofit2.http.*

interface DataApi {

    @GET("authors")
    suspend fun getAuthors(
        @Query("id") id: Int
    ): Response<ArrayList<Author>>

    @GET("posts")
    suspend fun getAuthorPosts(
        @Query("authorId") id: Int
    ): Response<Author>


}