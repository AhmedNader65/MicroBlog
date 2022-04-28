package com.shahry.microblogging.data.repository

import com.shahry.microblogging.data.dataSource.DataRemoteDataSource
import com.shahry.microblogging.model.Author
import com.shahry.microblogging.model.Post
import com.shahry.microblogging.model.Resource
import com.shahry.microblogging.model.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject

class DataRepository @Inject constructor(
    private val dataSource: DataRemoteDataSource
) {
    private val mDataMutex = Mutex()

    private var mAuthors: Resource<ArrayList<Author>>? = null

    suspend fun getAuthors(): Flow<Resource<ArrayList<Author>>?> {
        return flow {
            emit(Resource.loading())
            emit(getDataCached())
            val result = dataSource.getAuthors()
            if (result.status == Status.SUCCESS) {
                result.let {
                    mDataMutex.withLock {
                        mAuthors = it
                    }
                }
            }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getPosts(authorId: Int): Flow<Resource<ArrayList<Post>>?> {
        return flow {
            emit(Resource.loading())
            val result = dataSource.getAuthorPosts(authorId)
            emit(result)
        }.flowOn(Dispatchers.IO)
    }


    private suspend fun getDataCached(): Resource<ArrayList<Author>>? {
        return mDataMutex.withLock {
            this.mAuthors
        }
    }
}