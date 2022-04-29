package com.shahry.microblogging.data.dataSource

import com.shahry.microblogging.data.api.DataApi
import com.shahry.microblogging.getResponse
import com.shahry.microblogging.model.Author
import com.shahry.microblogging.model.Post
import com.shahry.microblogging.model.Resource
import javax.inject.Inject

class DataRemoteDataSource @Inject constructor(
    private val dataApi: DataApi
) {

    suspend fun getAuthors(): Resource<ArrayList<Author>> {
        return getResponse(
            request = { dataApi.getAuthors(1) },
            defaultErrorMessage = "Error fetching data"
        )
    }

    suspend fun getAuthorPosts(authorId: Int): Resource<ArrayList<Post>> {
        return getResponse(
            request = { dataApi.getAuthorPosts(authorId) },
            defaultErrorMessage = "Error downloading file"
        )
    }


}