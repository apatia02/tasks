package com.example.customview

import android.app.Application
import dagger.Component
import dagger.Subcomponent
import javax.inject.Inject
import javax.inject.Scope


@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ActivityScope

@ActivityScope
@Subcomponent
interface ActivityComponent {

    fun inject(activity: MainActivity)

    @Subcomponent.Factory
    interface Factory {
        fun create(): ActivityComponent
    }
}
class Presenter @Inject constructor(private val application: Application) : PresenterInterface {
    override fun getSizeList(): Int {
        return SIZE_LIST
    }

    companion object {
        const val SIZE_LIST = 40
    }
}


interface PresenterInterface {
    fun getSizeList(): Int
}