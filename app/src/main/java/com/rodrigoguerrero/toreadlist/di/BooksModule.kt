package com.rodrigoguerrero.toreadlist.di

import com.rodrigoguerrero.toreadlist.network.service.BookService
import com.rodrigoguerrero.toreadlist.repositories.BookRepository
import com.rodrigoguerrero.toreadlist.repositories.BookRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ViewModelComponent::class)
object BooksModule {
  @Provides
  fun provideBookService(): BookService {
    return Retrofit.Builder()
      .baseUrl("https://openlibrary.org/")
      .addConverterFactory(GsonConverterFactory.create())
      .build()
      .create(BookService::class.java)
  }

  @Provides
  fun provideBookRepository(bookService: BookService): BookRepository {
    return BookRepositoryImpl(bookService)
  }
}
