package com.shahry.microblogging.ui.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.shahry.microblogging.adapter.AuthorsAdapter
import com.shahry.microblogging.adapter.PostsAdapter
import com.shahry.microblogging.databinding.FragmentPostsBinding
import com.shahry.microblogging.model.Author
import com.shahry.microblogging.model.Post
import com.shahry.microblogging.model.Status
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.ArrayList


@AndroidEntryPoint
class PostsFragment : Fragment() {

    private lateinit var adapter: PostsAdapter
    private var _binding: FragmentPostsBinding? = null
    private val viewModel by viewModels<PostsViewModel>()
    private val args by navArgs<PostsFragmentArgs>()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentPostsBinding.inflate(inflater, container, false)
        viewModel.fetchAuthorPosts(args.author.id)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = PostsAdapter()
        binding.postsList.adapter = adapter
        collectData()
    }

    private fun collectData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.posts.collect {
                    when (it.status) {
                        Status.SUCCESS -> {
                            binding.loading.visibility = View.GONE

                            it.data?.let { posts ->
                                adapter.setList(posts)
                            }
                        }
                        Status.LOADING -> {
                            binding.loading.visibility = View.VISIBLE
                        }
                        Status.IDLE -> {
                            binding.loading.visibility = View.GONE
                        }
                        Status.ERROR -> {
                            binding.loading.visibility = View.GONE
                            it.message?.let { message ->
                                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}