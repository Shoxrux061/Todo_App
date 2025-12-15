package uz.test.todo.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uz.test.todo.data.local.room.AppDatabase
import uz.test.todo.data.local.room.TaskDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "tasks_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideTasksDao(db: AppDatabase): TaskDao {
        return db.tasksDao()
    }


}