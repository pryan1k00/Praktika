package com.example.praktika0102

data class Post(
    val title: String,
    val time: String,
    val content: String,
    val imageResId: Int,
    var likeCount: Int = 0,
    var repostCount: Int = 999,
    var viewCount: Int = 0,
    var isLiked: Boolean = false
) 