package com.samadihadis.quiz

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.samadihadis.quiz.databinding.ActivityMainBinding
import com.samadihadis.quiz.databinding.ActivityQuestionBinding

class QuestionActivity : AppCompatActivity() {


    lateinit var binding: ActivityQuestionBinding
    var currentQuestion = 0
    lateinit var questionList: ArrayList<Question>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        questionList = Constants.getQuestions()

        val question = questionList[currentQuestion]
        loadQuestionData(question)
    }

    fun loadQuestionData(question: Question) {

        binding.titleTextView.text = question.question
        binding.countryImageView.setImageResource(question.image)
        binding.optionOneTextView.text = question.optionOne
        binding.optionTwoTextView.text = question.optionTwo
        binding.optionThreeTextView.text = question.optionThree
        binding.optionFourTextView.text = question.optionFour
        binding.questionProgressBar.progress = currentQuestion + 1


        binding.stepTextView.text = "${currentQuestion + 1} / ${binding.questionProgressBar.max}"


    }

    fun setDefaultForOption() {
        var textViewOptions = ArrayList<TextView>()
        textViewOptions.add(binding.optionOneTextView)
        textViewOptions.add(binding.optionTwoTextView)
        textViewOptions.add(binding.optionThreeTextView)
        textViewOptions.add(binding.optionFourTextView)

        for (textViewOption in textViewOptions) {
            textViewOption.typeface = Typeface.DEFAULT

            textViewOption.background =
                ContextCompat.getDrawable(this, R.drawable.option_background_selected)
            textViewOption.setTextColor(Color.parseColor("#000000"))
        }
    }

    fun onOptionsClicked(view: View) {
        setDefaultForOption()
        var selectedTextView = view as TextView
        selectedTextView.typeface = Typeface.DEFAULT_BOLD
        selectedTextView.background =
            ContextCompat.getDrawable(this, R.drawable.option_background_selected)
        selectedTextView.setTextColor(Color.parseColor("#000000"))

    }
}