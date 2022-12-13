package com.ozimos.githubusercompose.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ozimos.githubusercompose.data.model.response.UserItem

@Database(entities = [UserItem::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun UserDao(): UserDao
}