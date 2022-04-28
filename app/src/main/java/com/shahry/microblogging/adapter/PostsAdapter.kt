package com.shahry.microblogging.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shahry.microblogging.databinding.PostItemBinding
import com.shahry.microblogging.model.Post

class PostsAdapter(
    private var mDataList: ArrayList<Post>,
) :
    RecyclerView.Adapter<PostsAdapter.ViewHolder>() {

    private lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val itemBinding =
            PostItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val post = mDataList[position]
    }


    override fun getItemCount(): Int {
        return mDataList.size
    }

    class ViewHolder(binding: PostItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val mBinding = binding
    }


}