package com.shahry.microblogging.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shahry.microblogging.databinding.AuthorItemBinding
import com.shahry.microblogging.model.Author

class AuthorsAdapter(
    private val listener: OnAuthorInteract,
    private var mDataList: ArrayList<Author> = arrayListOf()

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
        holder.mBinding.root.setOnClickListener {
            listener.onAuthorClick(author)
        }
        holder.bind(author)
    }


    override fun getItemCount(): Int {
        return mDataList.size
    }

    fun setList(mList: ArrayList<Author>) {
        mDataList = mList
        notifyDataSetChanged()
    }

    class ViewHolder(binding: AuthorItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val mBinding = binding
        fun bind(author: Author) {
            mBinding.authorName.text = author.name
            mBinding.authorUsername.text = author.userName
        }
    }


    interface OnAuthorInteract {
        fun onAuthorClick(author: Author)
    }
}