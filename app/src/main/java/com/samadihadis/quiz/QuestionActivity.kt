package com.samadihadis.quiz

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.samadihadis.quiz.databinding.ActivityQuestionBinding

class QuestionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuestionBinding

    private var currentQuestionIndex = 0
    private var selectedOption = 0
    private var correctAnswer = 0
    private var wrongAnswer = 0
    private var questionSize: Int = 0

    private lateinit var questionList: ArrayList<Question>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        questionList = Constants.getQuestions()

        questionSize = questionList.size

        loadQuestionData()


        val username = intent.getStringExtra(Constants.KEY_USERNAME)

        Toast.makeText(this, "welcome $username", Toast.LENGTH_LONG).show()

        binding.buttonShowResult.setOnClickListener {
            val intent = Intent(this@QuestionActivity, ResultActivity::class.java)
            intent.putExtra(Constants.KEY_USERNAME, username)
            intent.putExtra(Constants.KEY_WRONG_ANSWER, wrongAnswer)
            intent.putExtra(Constants.KEY_CORRECT_ANSWER, correctAnswer)
            startActivity(intent)
        }
    }

    private fun loadQuestionData() {

        val question = questionList[currentQuestionIndex]

        setupQuestionData(question)

        questionState()

    }

    private fun questionState() = with(binding) {
        with(questionProgressBar) {
            max = questionSize
            progress = currentQuestionIndex + 1
        }

        stepTextView.text = "${currentQuestionIndex + 1} / $questionSize"
    }

    private fun setupQuestionData(question: Question) = with(binding) {
        titleTextView.text = question.content
        countryImageView.setImageResource(question.image)
        optionOneTextView.text = question.optionOne
        optionTwoTextView.text = question.optionTwo
        optionThreeTextView.text = question.optionThree
        optionFourTextView.text = question.optionFour
    }


    private fun setDefaultForOption() {
        var textViewOptionList = ArrayList<TextView>().apply {
            with(binding) {
                add(optionOneTextView)
                add(optionTwoTextView)
                add(optionThreeTextView)
                add(optionFourTextView)
            }
        }

        for (textView in textViewOptionList) {
            with(textView) {
                typeface = Typeface.DEFAULT
                background =
                    ContextCompat.getDrawable(this@QuestionActivity, R.drawable.option_background)
                setTextColor(Color.parseColor("#000000"))
//                setTextColor(ContextCompat.getColor(this@QuestionActivity, R.color.textcolor))
            }
        }
    }

    fun onOptionsClicked(view: View) {
        setDefaultForOption()
        var selectedTextView = view as AppCompatTextView


        when (selectedTextView.tag.toString()) {
            "optionOne" -> {
                selectedOption = 1
            }

            "optionTwo" -> {
                selectedOption = 2
            }

            "optionThree" -> {
                selectedOption = 3
            }

            "optionFour" -> {
                selectedOption = 4
            }

        }
        with(selectedTextView) {
            typeface = Typeface.DEFAULT_BOLD
            background =
                ContextCompat.getDrawable(
                    this@QuestionActivity,
                    R.drawable.option_background_selected
                )
            setTextColor(Color.BLACK)
        }
    }

    fun submitAnswer(view: View) {
        if (selectedOption == 0) {
            binding.acceptButton.text = getString(R.string.selected_my_answer)
        }
        if (selectedOption == 0 && currentQuestionIndex < questionList.size - 1) {
            currentQuestionIndex++
            loadQuestionData()
            setDefaultForOption()
        } else {
            if (currentQuestionIndex == questionList.size - 1 && selectedOption == 0) {
                binding.acceptButton.visibility = View.GONE
                binding.buttonShowResult.visibility = View.VISIBLE
                return
            }
            checkAnswer()
            binding.acceptButton.text = getString(R.string.go_next_question)
            selectedOption = 0
            if (currentQuestionIndex == questionList.size - 1) {
                binding.acceptButton.visibility = View.GONE
                binding.buttonShowResult.visibility = View.VISIBLE
            }
        }
    }


    private fun checkAnswer() {
        if (selectedOption == questionList[currentQuestionIndex].correctAnswer) {
            setBackgroundTextView(selectedOption, R.drawable.option_background_correct)
            correctAnswer++
        } else {
            wrongAnswer++
            setBackgroundTextView(selectedOption, R.drawable.option_background_wrong)
            setBackgroundTextView(
                questionList[currentQuestionIndex].correctAnswer,
                R.drawable.option_background_correct
            )
        }
    }

    private fun setBackgroundTextView(optionIndex: Int, drawableResource: Int) = with(binding) {
        when (optionIndex) {
            1 -> {
                optionOneTextView.background =
                    ContextCompat.getDrawable(this@QuestionActivity, drawableResource)
            }

            2 -> {
                optionTwoTextView.background =
                    ContextCompat.getDrawable(this@QuestionActivity, drawableResource)
            }

            3 -> {
                optionThreeTextView.background =
                    ContextCompat.getDrawable(this@QuestionActivity, drawableResource)
            }

            4 -> {
                optionFourTextView.background =
                    ContextCompat.getDrawable(this@QuestionActivity, drawableResource)
            }
        }

    }

}