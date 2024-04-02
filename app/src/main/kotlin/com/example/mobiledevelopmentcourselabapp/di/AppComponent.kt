package com.example.mobiledevelopmentcourselabapp.di

import android.content.Context
import com.example.mobiledevelopmentcourselabapp.presentation.view.second.SecondFragment
import com.example.mobiledevelopmentcourselabapp.presentation.view.second.model.CardFragment
import dagger.BindsInstance
import dagger.Component
import java.time.LocalTime
import javax.inject.Singleton

@Component
@Singleton

interface AppComponent {

    fun inject(fragment: SecondFragment)

    fun inject(fragment: CardFragment)
    @Component.Factory
    interface Factory{
        fun create(@BindsInstance context: Context): AppComponent
    }
}