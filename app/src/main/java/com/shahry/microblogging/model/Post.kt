package com.shahry.microblogging.model

data class Post(
    val id: Int,
    val date: String,
    val title: String,
    val body: String,
    val imageUrl: String
)
