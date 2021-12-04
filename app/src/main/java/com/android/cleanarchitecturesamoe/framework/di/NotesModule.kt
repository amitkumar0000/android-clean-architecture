package com.android.cleanarchitecturesamoe.framework.di

import com.android.cleanarchitecturesamoe.framework.RoomDataSource
import com.android.cleanarchitecturesamoe.presentation.IListAction
import com.android.cleanarchitecturesamoe.presentation.ListFragment
import com.android.core.repository.INoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class NotesModule {
    @Binds
    abstract fun providesIListAction(listFragment: ListFragment): IListAction

    @Binds
    abstract fun provideINoteDataSource(roomDataSource: RoomDataSource): INoteDataSource
}