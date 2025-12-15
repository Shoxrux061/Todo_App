package uz.test.todo.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.test.todo.data.local.room.TaskDao
import uz.test.todo.data.repository.TaskRepositoryImpl
import uz.test.todo.domain.repository.TasksRepository
import uz.test.todo.domain.use_case.DeleteTaskByIdUseCase
import uz.test.todo.domain.use_case.GetAllTasksUseCase
import uz.test.todo.domain.use_case.GetTaskByIdUseCase
import uz.test.todo.domain.use_case.UpdateTaskUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    @Singleton
    fun provideTaskRepository(dao: TaskDao): TasksRepository {
        return TaskRepositoryImpl(dao)
    }

    @Provides
    @Singleton
    fun provideGetAllTaskUseCase(repository: TasksRepository): GetAllTasksUseCase {
        return GetAllTasksUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetTaskByIdUseCase(repository: TasksRepository): GetTaskByIdUseCase {
        return GetTaskByIdUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideUpdateTaskUseCase(repository: TasksRepository): UpdateTaskUseCase {
        return UpdateTaskUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideDeleteTaskByIdUseCase(repository: TasksRepository): DeleteTaskByIdUseCase {
        return DeleteTaskByIdUseCase(repository)
    }

}