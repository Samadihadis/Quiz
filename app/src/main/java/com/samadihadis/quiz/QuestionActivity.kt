package com.samadihadis.quiz

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.samadihadis.quiz.databinding.ActivityMainBinding
import com.samadihadis.quiz.databinding.ActivityQuestionBinding

class QuestionActivity : AppCompatActivity() {


    private lateinit var binding: ActivityQuestionBinding
    private var currentQuestion = 0
    private var selectedOption = 0
    private var correctAnswer = 0
    private var wrongAnswer = 0

    private lateinit var questionList: ArrayList<Question>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        questionList = Constants.getQuestions()


        loadQuestionData()
        val username = getIntent().getStringExtra("username")
        Toast.makeText(this, username, Toast.LENGTH_LONG).show()
        binding.buttonShowResult.setOnClickListener {
            val intent = Intent(this@QuestionActivity, ResultActivity::class.java)
            intent.putExtra("wrongAnswer", wrongAnswer)
            intent.putExtra("correctAnswer", correctAnswer)
            intent.putExtra("username", username)
            startActivity(intent)
        }
    }

    private fun loadQuestionData() {

        val question = questionList[currentQuestion]
        binding.titleTextView.text = question.question
        binding.countryImageView.setImageResource(question.image)
        binding.optionOneTextView.text = question.optionOne
        binding.optionTwoTextView.text = question.optionTwo
        binding.optionThreeTextView.text = question.optionThree
        binding.optionFourTextView.text = question.optionFour
        binding.questionProgressBar.progress = currentQuestion + 1


        binding.stepTextView.text = "${currentQuestion + 1} / ${binding.questionProgressBar.max}"


    }

    private fun setDefaultForOption() {
        var textViewOptions = ArrayList<TextView>()
        textViewOptions.add(binding.optionOneTextView)
        textViewOptions.add(binding.optionTwoTextView)
        textViewOptions.add(binding.optionThreeTextView)
        textViewOptions.add(binding.optionFourTextView)

        for (textViewOption in textViewOptions) {
            textViewOption.typeface = Typeface.DEFAULT

            textViewOption.background =
                ContextCompat.getDrawable(this, R.drawable.option_background)
            textViewOption.setTextColor(Color.parseColor("#000000"))
        }
    }

    fun onOptionsClicked(view: View) {
        setDefaultForOption()
        var selectedTextView = view as TextView


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
        selectedTextView.typeface = Typeface.DEFAULT_BOLD
        selectedTextView.background =
            ContextCompat.getDrawable(this, R.drawable.option_background_selected)
        selectedTextView.setTextColor(Color.parseColor("#000000"))

    }

    fun submitAnswer(view: View) {
        if (selectedOption == 0) {
            binding.acceptButton.text = "جوابمو انتخاب کردم !"
        }
        if (selectedOption == 0 && currentQuestion < questionList.size - 1) {
            currentQuestion++
            loadQuestionData()
            setDefaultForOption()
        } else {
            if (currentQuestion == questionList.size - 1 && selectedOption == 0) {
                binding.acceptButton.visibility = View.GONE
                binding.buttonShowResult.visibility = View.VISIBLE
                return
            }
            checkAnswer()
            binding.acceptButton.text = "برو به سوال بعدی"
            selectedOption = 0
            if (currentQuestion == questionList.size - 1) {
                binding.acceptButton.visibility = View.GONE
                binding.buttonShowResult.visibility = View.VISIBLE
            }
        }
    }


    private fun setBackgroundTextView(optionIndex: Int, drawableIndex: Int) {
        when (optionIndex) {
            1 -> {
                binding.optionOneTextView.background =
                    ContextCompat.getDrawable(this, drawableIndex)
            }

            2 -> {
                binding.optionTwoTextView.background =
                    ContextCompat.getDrawable(this, drawableIndex)
            }

            3 -> {
                binding.optionThreeTextView.background =
                    ContextCompat.getDrawable(this, drawableIndex)
            }

            4 -> {
                binding.optionFourTextView.background =
                    ContextCompat.getDrawable(this, drawableIndex)
            }
        }

    }

    private fun checkAnswer() {
        if (selectedOption == questionList[currentQuestion].correctAnswer) {
            setBackgroundTextView(selectedOption, R.drawable.option_background_correct)
            correctAnswer++
        } else {
            wrongAnswer++
            setBackgroundTextView(selectedOption, R.drawable.option_background_wrong)
            setBackgroundTextView(
                questionList[currentQuestion].correctAnswer,
                R.drawable.option_background_correct
            )
        }

    }
}