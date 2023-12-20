package com.example.customview

import android.annotation.SuppressLint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

@SuppressLint("CheckResult")
class QuizBinder {
    private val eventHub = EventHub()
    private val middleware = QuizMiddleware()
    private val reducer = QuizReducer()
    private val stateHolder = QuizStateHolder()

    init {
        eventHub.observeEvents()
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { event ->
                val currentState = stateHolder.get()
                val newState = reducer.reduce(currentState, event)
                stateHolder.update(newState)
            }
            .observeOn(Schedulers.io())
            .flatMap { event -> middleware.transform(event) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { event ->
                val currentState = stateHolder.get()
                val newState = reducer.reduce(currentState, event)
                stateHolder.update(newState)
            }
    }

    fun bind(view: QuizWidget) {
        stateHolder.subscribe()
            .subscribe { state ->
                view.renderState(state)
            }
    }

    fun emitEvent(event: QuizEvent) {
        eventHub.emitEvent(event)
    }
}