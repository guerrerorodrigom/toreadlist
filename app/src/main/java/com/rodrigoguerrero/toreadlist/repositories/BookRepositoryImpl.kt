package com.rodrigoguerrero.toreadlist.repositories

import com.rodrigoguerrero.toreadlist.models.Book
import com.rodrigoguerrero.toreadlist.models.NetworkResponse
import com.rodrigoguerrero.toreadlist.network.service.BookService
import com.rodrigoguerrero.toreadlist.storage.BookDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
  private val bookService: BookService,
  private val bookDataSource: BookDataSource
) : BookRepository {
  override fun search(query: String) = flow {
    emit(NetworkResponse.Loading)
    val response = bookService.search(query)
    if (response.isSuccessful && response.body() != null) {
      emit(NetworkResponse.Success(
        result = response.body()?.books?.map {
          Book(
            author = if (it.authors?.isNotEmpty() == true) it.authors[0] else "",
            title = it.title,
            coverId = it.coverId
          )
        } ?: emptyList())
      )
    }
  }
  .catch { emit(NetworkResponse.Error(it)) }
  .flowOn(Dispatchers.IO)

  override fun getBookList(): Flow<List<Book>> = bookDataSource.books

  override suspend fun addBook(book: Book) {
    bookDataSource.addBook(book)
  }
}
