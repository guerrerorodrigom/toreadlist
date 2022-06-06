package com.rodrigoguerrero.toreadlist.ui.components

import android.app.appsearch.SearchResults
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.rodrigoguerrero.toreadlist.models.SearchState
import com.rodrigoguerrero.toreadlist.models.SearchUiState

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchScreen(
  searchUiState: SearchUiState,
  onSearch: (String) -> Unit,
  modifier: Modifier = Modifier
) {
  var searchTerm by remember { mutableStateOf("") }
  val keyboardController = LocalSoftwareKeyboardController.current

  Column(modifier = modifier) {
    OutlinedTextField(
      value = searchTerm,
      onValueChange = { searchTerm = it },
      placeholder = { Text("Search a book") },
      keyboardOptions = KeyboardOptions.Default.copy(
        keyboardType = KeyboardType.Text,
        imeAction = ImeAction.Search
      ),
      keyboardActions = KeyboardActions(
        onSearch = {
          keyboardController?.hide()
          onSearch(searchTerm)
        }
      ),
      modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
    )
    when (searchUiState.state) {
      SearchState.LOADING -> ProgressBar()
      SearchState.DONE -> SearchResults(searchUiState)
    }
  }
}

@Composable
fun SearchResults(
  searchUiState: SearchUiState
) {
  LazyColumn {
    items(searchUiState.searchResult) { book ->
      BookRow(book = book)
    }
  }
}
