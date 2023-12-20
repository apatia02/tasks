package com.example.customview

sealed class QuizState {
    object Loading : QuizState()
    object Error : QuizState()
    data class Data(val question: String) : QuizState()
}