package com.example.mobiledevelopmentcourselabapp.di.module

import android.media.MediaPlayer
import com.example.mobiledevelopmentcourselabapp.di.provider.MediaPlayerProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MediaPlayerModule {
    @Provides
    @Singleton
    fun provideMediaPlayer(provider: MediaPlayerProvider): MediaPlayer {
        return provider.get()
    }
}