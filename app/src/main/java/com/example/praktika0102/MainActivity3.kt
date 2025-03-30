package com.example.praktika0102

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity3 : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PostAdapter
    private val posts = mutableListOf<Post>()
    private var isLoading = false
    private val initialPosts = listOf(
        Post(
            "Борисоглебский техникум промышленных и информационных технологий",
            "20 марта в 20:00",
            "В 2025 году отмечается 300-летие Воронежской губернии. В честь этого важного события мы предлагаем окунуться в историю региона, узнать больше о наших славных земляках и познакомиться с самыми неожиданными фактами о родном крае. В селе Алешки Терновского района (ранее – Борисоглебского уезда) в 1921-1924 годах получал образование знаменитый советский писатель Гавриил Троепольский. Автор повести «Белый Бим Черное ухо» окончил местное сельскохозяйственное училище (позднее ставшее зооветеринарным техникумом) по специальности «агроном» →",
            R.drawable.page1
        ),
        Post(
            "Борисоглебский техникум промышленных и информационных технологий",
            "15 марта в 17:46",
            "‍💻5 марта педагог-организатор Центра «САМ» Петухова Евгения Сергеевна провела семинар для кибердружины ГБПОУ ВО БТПИТ на тему «Ответственность за репосты и публикации в социальных сетях. Признаки деструктивного контента»",
            R.drawable.page2
        ),
        Post(
            "Борисоглебский техникум промышленных и информационных технологий",
            "10 марта в 15:30",
            "🎓 Студенты БТПИТ приняли участие в региональном этапе Всероссийской олимпиады профессионального мастерства обучающихся по специальности «Информационные системы и программирование»",
            R.drawable.page2
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main3)

        // Обработчик для кнопки "Назад"
        findViewById<TextView>(R.id.textView24).setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
            finish()
        }

        setupRecyclerView()
        loadInitialPosts()
        setupScrollListener()
    }

    private fun setupRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView)
        adapter = PostAdapter(
            posts,
            onLikeClick = { position ->
                val post = posts[position]
                post.isLiked = !post.isLiked
                post.likeCount += if (post.isLiked) 1 else -1
                adapter.notifyItemChanged(position)
            },
            onRepostClick = { position ->
                val post = posts[position]
                post.repostCount++
                adapter.notifyItemChanged(position)
            },
            onPostClick = { position ->
                val post = posts[position]
                showPostFragment(post)
            }
        )

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity3)
            adapter = this@MainActivity3.adapter
        }
    }

    private fun showPostFragment(post: Post) {
        val fragment = PostFragment.newInstance(
            post.title,
            post.time,
            post.content,
            post.imageResId,
            post.likeCount,
            post.repostCount,
            post.viewCount,
            post.isLiked
        )
        
        // Скрываем RecyclerView и показываем контейнер фрагмента
        recyclerView.visibility = View.GONE
        findViewById<View>(R.id.fragmentContainer).visibility = View.VISIBLE
        
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
            // Показываем RecyclerView и скрываем контейнер фрагмента
            recyclerView.visibility = View.VISIBLE
            findViewById<View>(R.id.fragmentContainer).visibility = View.GONE
        } else {
            super.onBackPressed()
        }
    }

    private fun loadInitialPosts() {
        posts.addAll(initialPosts)
        adapter.updatePosts(posts)
    }

    private fun setupScrollListener() {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if (!isLoading && (visibleItemCount + firstVisibleItemPosition) >= totalItemCount - 5
                    && firstVisibleItemPosition >= 0
                ) {
                    loadMorePosts()
                    isLoading = true
                }
            }
        })
    }

    private fun loadMorePosts() {
        // Имитация загрузки данных с сервера
        Handler(Looper.getMainLooper()).postDelayed({
            // Добавляем копии начальных постов
            posts.addAll(initialPosts.map { it.copy() })
            adapter.updatePosts(posts)
            isLoading = false
        }, 1500) // Задержка 1.5 секунды для имитации загрузки
    }

    override fun onResume() {
        super.onResume()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}