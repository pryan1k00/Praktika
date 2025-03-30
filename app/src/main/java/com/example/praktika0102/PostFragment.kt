package com.example.praktika0102

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.widget.ImageView
import android.widget.TextView

class PostFragment : Fragment() {
    private var postTitle: String? = null
    private var postDate: String? = null
    private var postContent: String? = null
    private var postImage: Int? = null
    private var postLikeCount: Int = 0
    private var postRepostCount: Int = 0
    private var postViewCount: Int = 0
    private var isLiked: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            postTitle = it.getString("title")
            postDate = it.getString("date")
            postContent = it.getString("content")
            postImage = it.getInt("image")
            postLikeCount = it.getInt("likeCount")
            postRepostCount = it.getInt("repostCount")
            postViewCount = it.getInt("viewCount")
            isLiked = it.getBoolean("isLiked")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_post, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<TextView>(R.id.textViewTitle).text = postTitle
        view.findViewById<TextView>(R.id.textViewDate).text = postDate
        view.findViewById<TextView>(R.id.textViewContent).text = postContent
        postImage?.let {
            view.findViewById<ImageView>(R.id.imageViewPost).setImageResource(it)
        }

        val likeButton = view.findViewById<ImageView>(R.id.imageViewLike)
        val likeCountText = view.findViewById<TextView>(R.id.textViewLikeCount)
        val repostButton = view.findViewById<ImageView>(R.id.imageViewRepost)
        val repostCountText = view.findViewById<TextView>(R.id.textViewRepostCount)
        val viewCountText = view.findViewById<TextView>(R.id.textViewViewCount)

        likeCountText.text = postLikeCount.toString()
        repostCountText.text = formatRepostCount(postRepostCount)
        viewCountText.text = postViewCount.toString()

        likeButton.setImageResource(if (isLiked) R.drawable.krlike else R.drawable.like)

        likeButton.setOnClickListener {
            isLiked = !isLiked
            postLikeCount += if (isLiked) 1 else -1
            likeButton.setImageResource(if (isLiked) R.drawable.krlike else R.drawable.like)
            likeCountText.text = postLikeCount.toString()
        }

        repostButton.setOnClickListener {
            postRepostCount++
            repostCountText.text = formatRepostCount(postRepostCount)
        }
    }

    private fun formatRepostCount(count: Int): String {
        return when {
            count >= 1000 -> "${count / 1000}K"
            else -> count.toString()
        }
    }

    companion object {
        fun newInstance(
            title: String,
            date: String,
            content: String,
            image: Int,
            likeCount: Int,
            repostCount: Int,
            viewCount: Int,
            isLiked: Boolean
        ) = PostFragment().apply {
            arguments = Bundle().apply {
                putString("title", title)
                putString("date", date)
                putString("content", content)
                putInt("image", image)
                putInt("likeCount", likeCount)
                putInt("repostCount", repostCount)
                putInt("viewCount", viewCount)
                putBoolean("isLiked", isLiked)
            }
        }
    }
} 