package com.example.praktika0102

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.ImageView
import android.widget.TextView
import android.annotation.SuppressLint


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
    private var kolRep11 = 0

    //Для второго репоста
    private lateinit var ivRepost22: ImageView
    private lateinit var tvKol22: TextView
    private var kolRep22 = 999

    //Для третьего репоста
    private lateinit var ivRepost33: ImageView
    private lateinit var tvKol33: TextView
    private var kolRep33 = 0

    @SuppressLint("DefaultLocale")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)

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

                //Для первого репоста
                ivRepost11 = findViewById(R.id.imageView6)
                tvKol11 = findViewById(R.id.textView7)
                ivRepost11.setOnClickListener {
                    kolRep11++
                    tvKol11.text = kolRep11.toString()
                }

                //Для второго репоста
                fun formatRepostCount(count: Int): String {
                    return when {
                        count >= 1000 -> "${count / 1000}K"
                        else -> count.toString()
                    }
                }

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
                ivRepost33.setOnClickListener {
                    kolRep33++
                    tvKol33.text = kolRep33.toString()
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

