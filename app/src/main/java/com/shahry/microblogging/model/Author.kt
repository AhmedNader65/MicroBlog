package com.shahry.microblogging.model

import java.io.Serializable

data class Author(val id: Int, val name: String, val email: String, val userName: String, val avatarUrl: String) :
    Serializable {}
