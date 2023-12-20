package com.example.customview

sealed class QuizEvent {
    object LoadEvent : QuizEvent()
    object ErrorEvent: QuizEvent()
    data class SuccessEvent(val question: String) : QuizEvent()
}