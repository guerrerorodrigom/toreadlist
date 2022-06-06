package com.rodrigoguerrero.toreadlist.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rodrigoguerrero.toreadlist.models.Book
import com.rodrigoguerrero.toreadlist.models.NetworkResponse
import com.rodrigoguerrero.toreadlist.models.SearchState
import com.rodrigoguerrero.toreadlist.models.SearchUiState
import com.rodrigoguerrero.toreadlist.repositories.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
  private val bookRepository: BookRepository
) : ViewModel() {

  private val _searchUiState = MutableStateFlow(SearchUiState())
  val searchUiState: Flow<SearchUiState>
    get() = _searchUiState

  fun search(query: String) {
    viewModelScope.launch {
        bookRepository
          .search(query)
          .collect { response ->
            when (response) {
              is NetworkResponse.Success -> {
                _searchUiState.emit(
                  SearchUiState(
                    state = SearchState.DONE,
                    searchResult = response.result ?: emptyList())
                )
              }
              NetworkResponse.Loading -> _searchUiState.emit(SearchUiState(SearchState.LOADING))
              is NetworkResponse.Error -> {}
            }
          }

    }
  }
}