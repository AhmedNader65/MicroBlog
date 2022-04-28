package com.shahry.microblogging.ui.posts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shahry.microblogging.data.repository.DataRepository
import com.shahry.microblogging.model.Author
import com.shahry.microblogging.model.Post
import com.shahry.microblogging.model.Resource
import com.shahry.microblogging.model.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(private val dataRepository: DataRepository) :
    ViewModel() {
    private var fetchAuthorPostsJob: Job? = null

    private val _posts = MutableStateFlow(Resource<ArrayList<Post>>(Status.IDLE, null, null))
    val posts: StateFlow<Resource<ArrayList<Post>>> = _posts.asStateFlow()


    fun fetchAuthorPosts(authorId: Int) {
        fetchAuthorPostsJob?.cancel()
        fetchAuthorPostsJob = viewModelScope.launch {
            dataRepository.getPosts(authorId).collect {
                if (it != null) {
                    _posts.emit(it)
                }
            }
        }
    }
}
