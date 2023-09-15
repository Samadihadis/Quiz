package com.samadihadis.quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.samadihadis.quiz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        binding.startButton.setOnClickListener {
            val input = binding.userNameEditText.text.toString()
            if (input.isEmpty()) {
                Toast.makeText(this, getString(R.string.please_enter_username), Toast.LENGTH_LONG).show()
            } else {
                val intent = Intent(this, QuestionActivity::class.java)
                startActivity(intent)
            }
        }

        // TODO: Please remove below codes
        binding.userNameEditText.setText("Hadis")
        binding.startButton.performClick()
    }

}