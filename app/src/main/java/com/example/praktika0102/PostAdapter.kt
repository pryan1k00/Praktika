package com.example.praktika0102

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class PostAdapter(
    private var posts: List<Post>,
    private val onLikeClick: (Int) -> Unit,
    private val onRepostClick: (Int) -> Unit,
    private val onPostClick: (Int) -> Unit
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
        val menuButton: ImageView = view.findViewById(R.id.imageViewMenu)
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

        holder.menuButton.setOnClickListener {
            showPostMenu(holder.itemView.context as AppCompatActivity, position)
        }

        holder.itemView.setOnClickListener {
            onPostClick(position)
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

    private fun showPostMenu(activity: AppCompatActivity, position: Int) {
        val dialogView = LayoutInflater.from(activity).inflate(R.layout.dialog_post_menu, null)
        val dialog = AlertDialog.Builder(activity)
            .setView(dialogView)
            .create()

        dialogView.findViewById<TextView>(R.id.textViewEdit).setOnClickListener {
            showEditDialog(activity, position)
            dialog.dismiss()
        }

        dialogView.findViewById<TextView>(R.id.textViewDelete).setOnClickListener {
            showDeleteConfirmation(activity, position)
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun showEditDialog(activity: AppCompatActivity, position: Int) {
        val post = posts[position]
        val dialogView = LayoutInflater.from(activity).inflate(R.layout.dialog_edit_post, null)
        val editText = dialogView.findViewById<EditText>(R.id.editTextPost)
        editText.setText(post.content)

        AlertDialog.Builder(activity)
            .setTitle("Редактировать пост")
            .setView(dialogView)
            .setPositiveButton("Сохранить") { _, _ ->
                val newContent = editText.text.toString()
                if (newContent.isNotEmpty()) {
                    val updatedPost = post.copy(content = newContent)
                    val newPosts = posts.toMutableList()
                    newPosts[position] = updatedPost
                    updatePosts(newPosts)
                }
            }
            .setNegativeButton("Отмена", null)
            .show()
    }

    private fun showDeleteConfirmation(activity: AppCompatActivity, position: Int) {
        AlertDialog.Builder(activity)
            .setTitle("Удалить пост")
            .setMessage("Вы уверены, что хотите удалить этот пост?")
            .setPositiveButton("Удалить") { _, _ ->
                val newPosts = posts.toMutableList()
                newPosts.removeAt(position)
                updatePosts(newPosts)
            }
            .setNegativeButton("Отмена", null)
            .show()
    }
} 