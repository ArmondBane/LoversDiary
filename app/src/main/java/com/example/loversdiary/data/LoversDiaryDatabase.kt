package com.example.loversdiary.data

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.loversdiary.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [User::class, Moment::class, Photo::class, Event::class], version = 1)
@TypeConverters(Converters::class)
abstract class LoversDiaryDatabase: RoomDatabase() {

    abstract fun momentDao(): MomentDao
    abstract fun eventDao(): EventDao
    abstract fun userDao(): UserDao
    abstract fun photoDao(): PhotoDao

    class Callback @Inject constructor(
        private val database: Provider<LoversDiaryDatabase>,
        @ApplicationScope private val applicationScope: CoroutineScope,
    ) : RoomDatabase.Callback() {

        @RequiresApi(Build.VERSION_CODES.O)
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            val momentDao = database.get().momentDao()
            val eventDao = database.get().eventDao()
            val userDao = database.get().userDao()
            val photoDao = database.get().photoDao()

            applicationScope.launch {
            }
        }
    }
}