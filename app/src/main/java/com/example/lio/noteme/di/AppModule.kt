package com.example.lio.noteme.di

import android.app.Application
import androidx.room.Room
import com.example.lio.noteme.feature_note.data.data_source.NoteDatabase
import com.example.lio.noteme.feature_note.data.repository.NoteRepositoryImpl
import com.example.lio.noteme.feature_note.domain.repository.NoteRepository
import com.example.lio.noteme.feature_note.domain.use_case.AddNoteUseCase
import com.example.lio.noteme.feature_note.domain.use_case.DeleteNoteUseCase
import com.example.lio.noteme.feature_note.domain.use_case.GetNotesUseCase
import com.example.lio.noteme.feature_note.domain.use_case.NotesUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: NoteDatabase): NoteRepository {
        return NoteRepositoryImpl(db.noteDao)
    }

    @Provides
    @Singleton
    fun providesNoteUseCases(repository: NoteRepository): NotesUseCases {
        return NotesUseCases(
            getNotesUseCase = GetNotesUseCase(repository),
            deleteNoteUseCase = DeleteNoteUseCase(repository),
            addNoteUseCase = AddNoteUseCase(repository)
        )
    }
}