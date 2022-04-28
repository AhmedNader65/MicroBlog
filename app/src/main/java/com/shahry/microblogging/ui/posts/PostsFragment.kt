package com.shahry.microblogging.ui.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.shahry.microblogging.adapter.AuthorsAdapter
import com.shahry.microblogging.adapter.PostsAdapter
import com.shahry.microblogging.databinding.FragmentPostsBinding
import com.shahry.microblogging.model.Author
import com.shahry.microblogging.model.Post
import java.util.ArrayList


class PostsFragment : Fragment() {

    private var _binding: FragmentPostsBinding? = null
    private val viewModel by viewModels<PostsViewModel>()


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentPostsBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.postsList.adapter = PostsAdapter(getDummyData())

    }

    private fun getDummyData(): ArrayList<Post> {
        return arrayListOf(
            Post(
                1,
                "2017-01-29",
                "Sunt qui voluptas necessitatibus iste explicabo et.",
                "Itaque ea et aut autem dignissimos voluptas. Esse architecto exercitationem quia aliquid explicabo amet aut. Sint reiciendis omnis cumque qui cum minus. Nemo eum quia et quaerat eius est incidunt. Sit non rerum aut fugiat animi quisquam voluptatibus et. Est eum laboriosam est sint est velit omnis exercitationem et.",
                ""
            ),
            Post(
                1,
                "2017-01-29",
                "Sunt qui voluptas necessitatibus iste explicabo et.",
                "Itaque ea et aut autem dignissimos voluptas. Esse architecto exercitationem quia aliquid explicabo amet aut. Sint reiciendis omnis cumque qui cum minus. Nemo eum quia et quaerat eius est incidunt. Sit non rerum aut fugiat animi quisquam voluptatibus et. Est eum laboriosam est sint est velit omnis exercitationem et.",
                ""
            ),
            Post(
                1,
                "2017-01-29",
                "Sunt qui voluptas necessitatibus iste explicabo et.",
                "Itaque ea et aut autem dignissimos voluptas. Esse architecto exercitationem quia aliquid explicabo amet aut. Sint reiciendis omnis cumque qui cum minus. Nemo eum quia et quaerat eius est incidunt. Sit non rerum aut fugiat animi quisquam voluptatibus et. Est eum laboriosam est sint est velit omnis exercitationem et.",
                ""
            ),
            Post(
                1,
                "2017-01-29",
                "Sunt qui voluptas necessitatibus iste explicabo et.",
                "Itaque ea et aut autem dignissimos voluptas. Esse architecto exercitationem quia aliquid explicabo amet aut. Sint reiciendis omnis cumque qui cum minus. Nemo eum quia et quaerat eius est incidunt. Sit non rerum aut fugiat animi quisquam voluptatibus et. Est eum laboriosam est sint est velit omnis exercitationem et.",
                ""
            ),
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}