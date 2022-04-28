package com.shahry.microblogging.ui.authors

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.shahry.microblogging.adapter.AuthorsAdapter
import com.shahry.microblogging.databinding.FragmentAuthorsBinding
import com.shahry.microblogging.model.Author
import java.util.ArrayList


class AuthorsFragment : Fragment(), AuthorsAdapter.OnAuthorInteract {

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
        binding.authorsList.adapter = AuthorsAdapter(getDummyData(),this)

    }

    private fun getDummyData(): ArrayList<Author> {
        return arrayListOf<Author>(
            Author(1,"Ahmed Nader","ahmed",""),
            Author(1,"Ahmed Nader","ahmed",""),
            Author(1,"Ahmed Nader","ahmed",""),
            Author(1,"Ahmed Nader","ahmed",""),
            Author(1,"Ahmed Nader","ahmed",""),
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onAuthorClick(author: Author) {
        TODO("Not yet implemented")
    }
}