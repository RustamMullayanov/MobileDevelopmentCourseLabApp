package com.example.mobiledevelopmentcourselabapp.di

import android.content.Context
import com.example.mobiledevelopmentcourselabapp.di.module.ApiModule
import com.example.mobiledevelopmentcourselabapp.di.module.DatabaseModule
import com.example.mobiledevelopmentcourselabapp.di.module.MediaPlayerModule
import com.example.mobiledevelopmentcourselabapp.di.module.RepositoryModule
import com.example.mobiledevelopmentcourselabapp.presentation.view.article.view.ArticleFragment
import com.example.mobiledevelopmentcourselabapp.presentation.view.list.view.CardFragment
import com.example.mobiledevelopmentcourselabapp.presentation.view.list.view.EditFragment
import com.example.mobiledevelopmentcourselabapp.presentation.view.list.view.ListFragment
import com.example.mobiledevelopmentcourselabapp.presentation.view.notifications.view.NotificationAddFragment
import com.example.mobiledevelopmentcourselabapp.presentation.view.notifications.view.NotificationsListFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Component(modules = [
    ApiModule::class,
    RepositoryModule::class,
    DatabaseModule::class,
    MediaPlayerModule::class
])
@Singleton
interface AppComponent {

    //Fragments
    fun inject(fragment: CardFragment)
    fun inject(fragment: ListFragment)
    fun inject(fragment: ArticleFragment)
    fun inject(fragment: EditFragment)
    fun inject(fragment: NotificationsListFragment)
    fun inject(fragment: NotificationAddFragment)

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance context: Context): AppComponent
    }
}