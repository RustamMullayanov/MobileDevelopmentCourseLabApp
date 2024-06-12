package com.example.mobiledevelopmentcourselabapp.di
import android.content.Context
import com.example.mobiledevelopmentcourselabapp.di.module.ApiModule
import com.example.mobiledevelopmentcourselabapp.di.module.RepositoryModule
import com.example.mobiledevelopmentcourselabapp.presentation.view.second.SecondFragment
import com.example.mobiledevelopmentcourselabapp.presentation.view.second.CardFragment
import com.example.mobiledevelopmentcourselabapp.presentation.view.third.ThirdFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton
@Component(modules = [ApiModule::class, RepositoryModule::class])
@Singleton
interface AppComponent {
    //Fragments
    fun inject(fragment: CardFragment)
    fun inject(fragment: SecondFragment)
    fun inject(fragment: ThirdFragment)
    @Component.Factory
    interface Factory {

        fun create(@BindsInstance context: Context): AppComponent
    }
}