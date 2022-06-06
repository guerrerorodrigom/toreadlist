package com.rodrigoguerrero.toreadlist.repositories

import com.rodrigoguerrero.toreadlist.models.Book
import com.rodrigoguerrero.toreadlist.models.NetworkResponse
import kotlinx.coroutines.flow.Flow

interface BookRepository {
  fun search(query: String): Flow<NetworkResponse<List<Book>>>
}