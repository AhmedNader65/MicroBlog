package com.shahry.microblogging.model

import androidx.recyclerview.widget.DiffUtil

class AuthorDiffCallback : DiffUtil.ItemCallback<Author>() {

    override fun areItemsTheSame(oldItem: Author, newItem: Author): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Author, newItem: Author): Boolean {
        return oldItem == newItem
    }
}
