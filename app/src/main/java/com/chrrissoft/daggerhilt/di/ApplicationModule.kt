package com.chrrissoft.daggerhilt.di

import android.content.Context
import com.chrrissoft.daggerhilt.DaggerHiltApp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {
    @Provides
    fun provide(@ApplicationContext app: Context): DaggerHiltApp {
        return app as DaggerHiltApp
    }
}
