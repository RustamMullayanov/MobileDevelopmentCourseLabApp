package com.example.mobiledevelopmentcourselabapp

import java.time.Instant
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalTime @Inject constructor(){

    fun datetime() : Instant{
        return Instant.now()
    }

}