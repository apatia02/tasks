package com.example.customview

import android.app.Activity
import android.app.Application
import android.os.Bundle
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

@Module
class AppModule(private val application: Application) {

    @Provides
    fun provideApplication(): Application {
        return application
    }

    @Provides
    fun providePresenter(): PresenterInterface {
        return Presenter(application)
    }
}

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(application: Application)

    fun activityComponent(): ActivityComponent.Factory
}