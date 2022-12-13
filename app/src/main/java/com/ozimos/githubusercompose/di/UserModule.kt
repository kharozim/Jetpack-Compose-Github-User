package com.ozimos.githubusercompose.di

import android.content.Context
import androidx.room.Room
import com.ozimos.githubusercompose.data.local.AppDatabase
import com.ozimos.githubusercompose.data.local.UserDao
import com.ozimos.githubusercompose.data.remote.NetworkClient
import com.ozimos.githubusercompose.data.remote.UserService
import com.ozimos.githubusercompose.data.repo.IUserRepo
import com.ozimos.githubusercompose.data.repo.UserRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserModule {

    @Provides
    fun getDatabase(@ApplicationContext context: Context): AppDatabase {
        val nameDb = "github_user_db"
        return Room.databaseBuilder(
            context, AppDatabase::class.java,
            nameDb
        ).build()

    }

    @Provides
    fun provideUserDao(appDatabase: AppDatabase): UserDao {
        return appDatabase.UserDao()
    }

    @Provides
    @Singleton
    fun provideUserService(): UserService {
        return NetworkClient.userService
    }

    @Provides
    fun provideRepository(iUserRepo: IUserRepo): UserRepo {
        return iUserRepo
    }
}