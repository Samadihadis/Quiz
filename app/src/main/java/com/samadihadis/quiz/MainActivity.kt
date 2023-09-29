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

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupClickListener()

    }

    private fun setupClickListener() {
        binding.startButton.setOnClickListener {
            val input = binding.userNameEditText.text.toString()
            if (input.isEmpty()) {
                binding.userNameEditText.error = getString(R.string.please_enter_username)
            } else {
                val intent = Intent(this, QuestionActivity::class.java)
                intent.putExtra(Constants.KEY_USERNAME , input)
                startActivity(intent)
            }
        }
    }

//    private fun isEmpty2(charSequence: CharSequence): Boolean = charSequence.length == 0

//    private fun CharSequence.isEmpty2(): Boolean = length == 0

}