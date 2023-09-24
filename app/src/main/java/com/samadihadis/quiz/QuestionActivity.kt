package com.samadihadis.quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.samadihadis.quiz.databinding.ActivityMainBinding
import com.samadihadis.quiz.databinding.ActivityQuestionBinding

class QuestionActivity : AppCompatActivity() {


    lateinit var binding: ActivityQuestionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }


    private fun setQuestion(question: Question){
        with(binding) {
            titleTextView.text = question.question
            countryImageView.setImageResource(question.image)
            optionOneTextView.text = question.optionOne
            optionTwoTextView.text = question.optionTwo
            optionThreeTextView.text = question.optionThree
            optionFourTextView.text = question.optionFour
        }
    }
}