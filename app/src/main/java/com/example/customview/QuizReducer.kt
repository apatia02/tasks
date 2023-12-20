package com.example.customview

class QuizReducer {
    fun reduce(currentState: QuizState, event: QuizEvent): QuizState {
        return when (event) {
            is QuizEvent.LoadEvent -> QuizState.Loading
            is QuizEvent.ErrorEvent -> QuizState.Error
            is QuizEvent.SuccessEvent -> QuizState.Data(event.question)
        }
    }
}