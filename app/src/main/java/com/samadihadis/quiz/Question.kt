package com.samadihadis.quiz

data class Question(
    val id : Int,
    val content : String,
    val image : Int,
    val optionOne : String,
    val optionTwo : String,
    val optionThree : String,
    val optionFour : String,
    val correctAnswer : Int
)