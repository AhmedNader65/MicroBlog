package com.shahry.microblogging.data.dataSource

import androidx.paging.*
import com.shahry.microblogging.data.api.DataApi
import com.shahry.microblogging.getResponse
import com.shahry.microblogging.model.Author
import com.shahry.microblogging.model.Post
import com.shahry.microblogging.model.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

const val STARTING_PAGE_INDEX = 1
const val NETWORK_PAGE_SIZE = 10

class PagingDataSource @Inject constructor(
    private val dataApi: DataApi
) : PagingSource<Int, Author>() {

    override fun getRefreshKey(state: PagingState<Int, Author>): Int? {

        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    fun getDataSource(): Flow<PagingData<Author>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                this
            }
        ).flow
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Author> {
        val pageIndex = params.key ?: STARTING_PAGE_INDEX
        return try {
            val authors = getResponse(
                { dataApi.getAuthors(pageIndex) },
                "error"
            ).data!!
            val nextKey =
                if (authors.isNullOrEmpty()) {
                    null
                } else {
                    // By default, initial load size = 3 * NETWORK PAGE SIZE
                    // ensure we're not requesting duplicating items at the 2nd request
                    pageIndex + (params.loadSize / NETWORK_PAGE_SIZE)
                }
            LoadResult.Page(
                data = authors,
                prevKey = if (pageIndex == STARTING_PAGE_INDEX) null else pageIndex,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }


}