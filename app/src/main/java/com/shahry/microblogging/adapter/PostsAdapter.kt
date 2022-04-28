package com.shahry.microblogging.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shahry.microblogging.databinding.PostItemBinding
import com.shahry.microblogging.model.Author
import com.shahry.microblogging.model.Post

class PostsAdapter(
    private var mDataList: ArrayList<Post> = arrayListOf(),
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
        holder.bind(post)
    }

    fun setList(mList: ArrayList<Post>) {
        mDataList = mList
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return mDataList.size
    }

    class ViewHolder(binding: PostItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val mBinding = binding
        fun bind(post: Post) {
            mBinding.title.text = post.title
            mBinding.body.text = post.body
            mBinding.date.text = post.date
            Glide.with(mBinding.root.context).load(post.imageUrl).into(mBinding.image)
        }
    }


}