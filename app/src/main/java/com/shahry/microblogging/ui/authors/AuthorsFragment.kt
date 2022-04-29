package com.shahry.microblogging.ui.authors

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.shahry.microblogging.adapter.AuthorsAdapter
import com.shahry.microblogging.databinding.FragmentAuthorsBinding
import com.shahry.microblogging.model.Author
import com.shahry.microblogging.model.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.ArrayList


@AndroidEntryPoint
class AuthorsFragment : Fragment(), AuthorsAdapter.OnAuthorInteract {

    private lateinit var adapter: AuthorsAdapter
    private var _binding: FragmentAuthorsBinding? = null
    private val viewModel by viewModels<AuthorsViewModel>()


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAuthorsBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = AuthorsAdapter(this)
        binding.authorsList.adapter = adapter
        collectData()
    }

    private fun collectData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.fetchAuthors().collectLatest  {
                    binding.loading.visibility = GONE
                    adapter.submitData(it)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onAuthorClick(author: Author) {
        findNavController().navigate(
            AuthorsFragmentDirections.actionNavigationAuthorsToPostsFragment(
                author
            )
        )
    }
}