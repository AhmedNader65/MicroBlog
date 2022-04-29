package com.shahry.microblogging.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shahry.microblogging.databinding.AuthorItemBinding
import com.shahry.microblogging.model.Author
import com.shahry.microblogging.model.AuthorDiffCallback

class AuthorsAdapter(
    private val listener: OnAuthorInteract
) :
    PagingDataAdapter<Author, AuthorsAdapter.ViewHolder>(AuthorDiffCallback()) {

    private lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val itemBinding =
            AuthorItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

//        val author = mDataList[position]
        holder.mBinding.root.setOnClickListener {
            getItem(position)?.let { it1 -> listener.onAuthorClick(it1) }
        }
        getItem(position)?.let { holder.bind(it) }
    }


//
//    fun setList(mList: ArrayList<Author>) {
//        mDataList = mList
//        notifyDataSetChanged()
//    }

    class ViewHolder(binding: AuthorItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val mBinding = binding
        fun bind(author: Author) {
            mBinding.authorName.text = author.name
            mBinding.authorUsername.text = author.userName
            Glide.with(mBinding.root.context).load(author.avatarUrl).into(mBinding.image)
        }
    }


    interface OnAuthorInteract {
        fun onAuthorClick(author: Author)
    }
}