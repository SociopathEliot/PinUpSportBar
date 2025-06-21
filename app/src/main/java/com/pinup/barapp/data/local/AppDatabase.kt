package com.pinup.barapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pinup.barapp.domain.models.CartItem
import com.pinup.barapp.domain.models.Reservation

@Database(
    entities = [CartItem::class, Reservation::class],
    version = 3,
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun cartDao(): CartDao
    abstract fun reservationDao(): ReservationDao

    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "cart_db"
                )
                    .fallbackToDestructiveMigration()
                    .build().also { instance = it }
            }
    }

}
