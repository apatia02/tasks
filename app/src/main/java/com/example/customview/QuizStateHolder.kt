package com.example.customview

import android.database.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject

class QuizStateHolder {
    private val stateSubject = BehaviorSubject.createDefault(initialState)

    fun subscribe(): io.reactivex.rxjava3.core.Observable<QuizState> {
        return stateSubject.hide()
    }

    fun get(): QuizState {
        return stateSubject.value ?: initialState
    }

    fun update(newState: QuizState) {
        stateSubject.onNext(newState)
    }

    companion object {
        private val initialState: QuizState = QuizState.Loading
    }
}