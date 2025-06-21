package com.pinup.barapp.di

import android.content.Context
import androidx.room.Room
import com.pinup.barapp.data.local.AppDatabase
import com.pinup.barapp.data.local.CartDao
import com.pinup.barapp.data.local.ReservationDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "cart_db"
        ).build()
    }

    @Provides
    fun provideCartDao(db: AppDatabase): CartDao = db.cartDao()

    @Provides
    fun provideReservationDao(db: AppDatabase): ReservationDao = db.reservationDao()
}
