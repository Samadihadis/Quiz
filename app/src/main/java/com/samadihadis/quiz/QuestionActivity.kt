package com.samadihadis.quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.samadihadis.quiz.databinding.ActivityMainBinding
import com.samadihadis.quiz.databinding.ActivityQuestionBinding

class QuestionActivity : AppCompatActivity() {


    private lateinit var binding: ActivityQuestionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        val question1 = Question(
            1, "این پرچم متعلق به چه کشوری است  ؟‌", R.drawable.ic_flag_of_argentina,
            "ایران", "عربستان", "ترکیه", "آرژانتین", 4
        )

        setQuestion(question1)

    }


    private fun setQuestion(question: Question){
        with(binding) {
            titleTextView.setText(question.question)
            countryImageView.setImageResource(question.image)
            optionOneTextView.setText(question.optionOne)
            optionTwoTextView.setText(question.optionTwo)
            optionThreeTextView.setText(question.optionThree)
            optionFourTextView.setText(question.optionFour)
        }
    }
}