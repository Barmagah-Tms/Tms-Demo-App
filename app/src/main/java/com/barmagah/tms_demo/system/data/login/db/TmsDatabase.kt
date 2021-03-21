package com.barmagah.tms_demo.system.data.login.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.barmagah.tms_demo.system.data.login.LoginResponseEntry


@Database(
    entities = [LoginResponseEntry::class],
    version = 1
)
abstract class TmsDatabase : RoomDatabase() {
    abstract fun currentUserDao(): CurrentUserDao

    companion object {
        @Volatile
        private var instance: TmsDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                TmsDatabase::class.java, "tms_db.db"
            )
                .build()
    }
}