package com.samadihadis.quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.samadihadis.quiz.databinding.ActivityMainBinding
import com.samadihadis.quiz.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    lateinit var binding: ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username =getIntent().getStringExtra("username")
        val wrongIndex = getIntent().getIntExtra("wrongAnswer", 0)
        val correctIndex = getIntent().getIntExtra("correctAnswer", 0)

        binding.textViewCorrect.text = correctIndex.toString()
        binding.textViewWrong.text = wrongIndex.toString()
        binding.textViewUsername.text =username
    }
}