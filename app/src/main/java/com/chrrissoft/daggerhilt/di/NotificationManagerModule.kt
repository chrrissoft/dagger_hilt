package com.chrrissoft.daggerhilt.di

import android.content.Context
import androidx.core.app.NotificationManagerCompat
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NotificationManagerModule {
    @Provides
    fun provide(@ApplicationContext ctx: Context): NotificationManagerCompat {
        return NotificationManagerCompat.from(ctx)
    }
}
