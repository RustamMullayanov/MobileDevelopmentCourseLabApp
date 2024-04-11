package com.example.mobiledevelopmentcourselabapp.di.module
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.mobiledevelopmentcourselabapp.data.api.ChuckApi
import com.example.mobiledevelopmentcourselabapp.provider.ChuckApiProvider
import dagger.Module
import dagger.Provides
@Module
class ApiModule {
    @RequiresApi(Build.VERSION_CODES.O)
    @Provides
    fun chuckApiProvider(provider : ChuckApiProvider): ChuckApi {
        return provider.chuckApi
    }
}