package com.example.customview

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.subjects.PublishSubject

class EventHub {
    private val events = PublishSubject.create<QuizEvent>()
    fun observeEvents(): Observable<QuizEvent> {
        return events
    }

    fun emitEvent(event: QuizEvent) {
        events.onNext(event)
    }
}