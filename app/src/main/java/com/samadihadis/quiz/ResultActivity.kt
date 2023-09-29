package com.samadihadis.quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.samadihadis.quiz.databinding.ActivityMainBinding
import com.samadihadis.quiz.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra(Constants.KEY_USERNAME)
        val wrongIndex = intent.getIntExtra(Constants.KEY_WRONG_ANSWER, 0)
        val correctIndex = intent.getIntExtra(Constants.KEY_CORRECT_ANSWER, 0)

        with(binding) {
            textViewCorrect.text = correctIndex.toString()
            textViewWrong.text = wrongIndex.toString()
            textViewUsername.text = username
        }

    }
}