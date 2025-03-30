package com.example.praktika0102

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.ImageView
import android.widget.TextView
import android.annotation.SuppressLint
import org.json.JSONArray
import java.io.IOException

class MainActivity2 : AppCompatActivity() {
    //Для первого поста
    private lateinit var ivLike1: ImageView
    private lateinit var tvKol1: TextView
    private var likeKol1 = 0
    private var isLike1 = false

    //Для второго поста
    private lateinit var ivLike2: ImageView
    private lateinit var tvKol2: TextView
    private var likeKol2 = 0
    private var isLike2 = false

    //Для третьего поста
    private lateinit var ivLike3: ImageView
    private lateinit var tvKol3: TextView
    private var likeKol3 = 0
    private var isLike3 = false

    //Для первого репоста
    private lateinit var ivRepost11: ImageView
    private lateinit var tvKol11: TextView
    private var kolRep11 = 999

    //Для второго репоста
    private lateinit var ivRepost22: ImageView
    private lateinit var tvKol22: TextView
    private var kolRep22 = 999

    //Для третьего репоста
    private lateinit var ivRepost33: ImageView
    private lateinit var tvKol33: TextView
    private var kolRep33 = 999

    @SuppressLint("DefaultLocale")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)

        fun loadJsonData() {
            val fileName = "data.json.txt" // Название файла
            try {
                val jsonString = applicationContext.assets.open(fileName).bufferedReader().use { it.readText() }
                val jsonArray = JSONArray(jsonString)

                for (i in 0 until jsonArray.length()) {
                    val jsonObject = jsonArray.getJSONObject(i)
                    val sost = jsonObject.getString("sost") // Получаем строку "sost"
                    val pr = jsonObject.getInt("pr") // Получаем число "pr"

                    Log.d("Processing", "Состояние: $sost, Проверка: $pr") // Логирование данных
                }
            } catch (ex: IOException) {
                Log.e("MainActivity2", "Ошибка чтения файла", ex)
                ex.printStackTrace()
            } catch (ex: Exception) {
                Log.e("MainActivity2", "Общая ошибка", ex)
                ex.printStackTrace()
            }
        }



        // Добавляем обработчик для перехода на MainActivity3
        val goToSecondActivityImageView: ImageView = findViewById(R.id.imageView2)
        goToSecondActivityImageView.setOnClickListener {
            // Создаем Intent для перехода в SecondActivity
            val intent = Intent(this, MainActivity3::class.java)
            startActivity(intent) // Запускаем SecondActivity
        }

        // Добавляем обработчики для перехода на видео
        val imageView9: ImageView = findViewById(R.id.imageView9)
        val imageViewPlay: ImageView = findViewById(R.id.imageViewPlay)
        val videoUrl = "https://www.youtube.com/watch?v=ZnWwvFD8UqY"

        val videoIntent = Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl))

        imageView9.setOnClickListener {
            startActivity(videoIntent)
        }

        imageViewPlay.setOnClickListener {
            startActivity(videoIntent)
        }

        //Для первого поста
        ivLike1 = findViewById(R.id.imageView4)
        tvKol1 = findViewById(R.id.textView6)

        ivLike1.setOnClickListener {
            isLike1 = !isLike1
            if (isLike1) {
                likeKol1++
                ivLike1.setImageResource(R.drawable.krlike)
            } else {
                likeKol1--
                ivLike1.setImageResource(R.drawable.like)
            }
            tvKol1.text = likeKol1.toString()
        }

        //Для второго поста
        ivLike2 = findViewById(R.id.imageView10)
        tvKol2 = findViewById(R.id.textView15)

        ivLike2.setOnClickListener {
            isLike2 = !isLike2
            if (isLike2) {
                likeKol2++
                ivLike2.setImageResource(R.drawable.krlike)
            } else {
                likeKol2--
                ivLike2.setImageResource(R.drawable.like)
            }
            tvKol2.text = likeKol2.toString()
        }

        //Для третьего поста
        ivLike3 = findViewById(R.id.imageView18)
        tvKol3 = findViewById(R.id.textView21)

        ivLike3.setOnClickListener {
            isLike3 = !isLike3
            if (isLike3) {
                likeKol3++
                ivLike3.setImageResource(R.drawable.krlike)
            } else {
                likeKol3--
                ivLike3.setImageResource(R.drawable.like)
            }
            tvKol3.text = likeKol3.toString()
        }

        //Из 999 в 1к
        fun formatRepostCount(count: Int): String {
            return when {
                count >= 1000 -> "${count / 1000}K"
                else -> count.toString()
            }
        }

        //Для первого репоста
        ivRepost11 = findViewById(R.id.imageView6)
        tvKol11 = findViewById(R.id.textView7)
        tvKol11.text = formatRepostCount(kolRep11)

        ivRepost11.setOnClickListener {
            kolRep11++
            tvKol11.text = formatRepostCount(kolRep11)
        }

        //Для второго поста
        ivRepost22 = findViewById(R.id.imageView11)
        tvKol22 = findViewById(R.id.textView13)
        tvKol22.text = formatRepostCount(kolRep22)

        ivRepost22.setOnClickListener {
            kolRep22++
            tvKol22.text = formatRepostCount(kolRep22)
        }

        //Для третьего репоста
        ivRepost33 = findViewById(R.id.imageView)
        tvKol33 = findViewById(R.id.textView4)
        tvKol33.text = formatRepostCount(kolRep33)

        ivRepost33.setOnClickListener {
            kolRep33++
            tvKol33.text = formatRepostCount(kolRep33)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main1)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )
            insets
        }
    }
}

