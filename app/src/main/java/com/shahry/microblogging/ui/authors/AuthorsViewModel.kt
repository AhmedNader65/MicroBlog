package com.shahry.microblogging.ui.authors

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.shahry.microblogging.data.repository.DataRepository
import com.shahry.microblogging.model.Author
import com.shahry.microblogging.model.Resource
import com.shahry.microblogging.model.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import okhttp3.internal.wait
import javax.inject.Inject

@HiltViewModel
class AuthorsViewModel @Inject constructor(private val dataRepository: DataRepository) :
    ViewModel() {

    private lateinit var results: Flow<PagingData<Author>>

    fun fetchAuthors(): Flow<PagingData<Author>> {
        if (!this::results.isInitialized)
            results = dataRepository.getAuthors().cachedIn(viewModelScope)
        return results
    }
}
