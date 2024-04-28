package com.example.mobiledevelopmentcourselabapp.di.provider

import android.media.AudioAttributes
import android.media.MediaPlayer
import javax.inject.Inject
import javax.inject.Provider

class MediaPlayerProvider @Inject constructor(): Provider<MediaPlayer> {
    override fun get(): MediaPlayer {
        return MediaPlayer().apply {
            val url =
                "https://commondatastorage.googleapis.com/codeskulptor-demos/pyman_assets/ateapill.ogg"
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
            isLooping = true
            setDataSource(url)
        }
    }
}