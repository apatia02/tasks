package com.example.customview

import com.example.customview.RetrofitApi.jokeService
import io.reactivex.rxjava3.core.Observable
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit
import kotlin.random.Random


interface JokeService {
    @GET("/api?format=json")
    fun getRandomJoke(): Observable<JokeResponse>
}

data class JokeResponse(val joke: String?)

object RetrofitApi {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://geek-jokes.sameerkumar.website")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()
    val jokeService: JokeService = retrofit.create(JokeService::class.java)

}

class QuizMiddleware {
    fun transform(quizEvent: QuizEvent): Observable<out QuizEvent> {
        return when (quizEvent) {
            is QuizEvent.LoadEvent -> {
                jokeService.getRandomJoke()
                    .delay(1000, TimeUnit.MILLISECONDS)
                    .map { response -> if (Random.nextFloat() > 0.1) response else JokeResponse(null) }
                    .map { response ->
                        if (response.joke == null) QuizEvent.ErrorEvent
                        else QuizEvent.SuccessEvent(response.joke)
                    }
            }

            else -> Observable.just(quizEvent)
        }
    }
}