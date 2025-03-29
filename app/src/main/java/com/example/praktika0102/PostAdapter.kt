package com.example.praktika0102

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PostAdapter(
    private var posts: List<Post>,
    private val onLikeClick: (Int) -> Unit,
    private val onRepostClick: (Int) -> Unit
) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    class PostViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.textViewTitle)
        val time: TextView = view.findViewById(R.id.textViewTime)
        val content: TextView = view.findViewById(R.id.textViewContent)
        val image: ImageView = view.findViewById(R.id.imageViewPost)
        val likeButton: ImageView = view.findViewById(R.id.imageViewLike)
        val repostButton: ImageView = view.findViewById(R.id.imageViewRepost)
        val viewButton: ImageView = view.findViewById(R.id.imageViewView)
        val likeCount: TextView = view.findViewById(R.id.textViewLikeCount)
        val repostCount: TextView = view.findViewById(R.id.textViewRepostCount)
        val viewCount: TextView = view.findViewById(R.id.textViewViewCount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_post, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = posts[position]
        
        holder.title.text = post.title
        holder.time.text = post.time
        holder.content.text = post.content
        holder.image.setImageResource(post.imageResId)
        holder.likeCount.text = post.likeCount.toString()
        holder.repostCount.text = formatRepostCount(post.repostCount)
        holder.viewCount.text = post.viewCount.toString()
        
        holder.likeButton.setImageResource(
            if (post.isLiked) R.drawable.krlike else R.drawable.like
        )

        holder.likeButton.setOnClickListener {
            onLikeClick(position)
        }

        holder.repostButton.setOnClickListener {
            onRepostClick(position)
        }
    }

    override fun getItemCount() = posts.size

    fun updatePosts(newPosts: List<Post>) {
        posts = newPosts
        notifyDataSetChanged()
    }

    private fun formatRepostCount(count: Int): String {
        return when {
            count >= 1000 -> "${count / 1000}K"
            else -> count.toString()
        }
    }
} 