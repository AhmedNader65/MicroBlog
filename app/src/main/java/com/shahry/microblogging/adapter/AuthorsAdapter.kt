package com.shahry.microblogging.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shahry.microblogging.databinding.AuthorItemBinding
import com.shahry.microblogging.model.Author

class AuthorsAdapter(
    private var mDataList: ArrayList<Author>,
    private val listener: OnAuthorInteract
) :
    RecyclerView.Adapter<AuthorsAdapter.ViewHolder>() {

    private lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val itemBinding =
            AuthorItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val author = mDataList[position]
    }


    override fun getItemCount(): Int {
        return mDataList.size
    }

    class ViewHolder(binding: AuthorItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val mBinding = binding
    }


    interface OnAuthorInteract {
        fun onAuthorClick(author: Author)
    }
}