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
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthorsViewModel @Inject constructor(private val dataRepository: DataRepository) :
    ViewModel() {
    init {
        fetchAuthors()
    }

    private var fetchAuthorsJob: Job? = null

    private val _authors = MutableStateFlow(Resource<ArrayList<Author>>(Status.IDLE, null, null))
    val authors: StateFlow<Resource<ArrayList<Author>>> = _authors.asStateFlow()


    fun fetchAuthors(): Flow<PagingData<Author>> {
        return dataRepository.getAuthors()
            .map { pagingData ->
                pagingData.map {
                    it
                }
            }
            .cachedIn(viewModelScope)
    }
//    private fun fetchAuthors() {
//        fetchAuthorsJob?.cancel()
//        fetchAuthorsJob = viewModelScope.launch {
//            dataRepository.getAuthors().collect {
//                if (it != null) {
//                    _authors.emit(it)
//                }
//            }
//        }
//    }
}
