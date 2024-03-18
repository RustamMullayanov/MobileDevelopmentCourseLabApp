package com.example.mobiledevelopmentcourselabapp.di.module

import com.example.mobiledevelopmentcourselabapp.data.api.ChuckApi
import com.example.mobiledevelopmentcourselabapp.data.api.NinjaApi
import com.example.mobiledevelopmentcourselabapp.data.provider.ChuckApiProvider
import com.example.mobiledevelopmentcourselabapp.data.provider.NinjaApiProvider
import dagger.Module
import dagger.Provides

@Module
class ApiModule {
    @Provides
    fun provideChuckApi(retrofitProvider: ChuckApiProvider): ChuckApi {
        return retrofitProvider.retrofitChuckApi
    }

    @Provides
    fun provideNinjaApi(retrofitProvider: NinjaApiProvider): NinjaApi {
        return retrofitProvider.retrofitNinjaApi
    }
}
