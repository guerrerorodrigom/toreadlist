/*
 * Copyright (c) 2022 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.yourcompany.toreadlist.ui.components

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.yourcompany.toreadlist.models.Book
import com.yourcompany.toreadlist.models.SearchState
import com.yourcompany.toreadlist.models.SearchUiState

@Composable
fun SearchScreen(
  searchUiState: SearchUiState,
  onSearch: (String) -> Unit,
  onAddToList: (Book) -> Unit,
  onBackPressed: () -> Unit,
  modifier: Modifier = Modifier
) {
  var searchTerm by remember { mutableStateOf("") }

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
          onSearch(searchTerm)
        },
        onDone = {
          onSearch(searchTerm)
        }
      ),
      singleLine = true,
      modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
    )
    when (searchUiState.state) {
      SearchState.LOADING -> ProgressBar()
      SearchState.DONE -> SearchResults(searchUiState, onAddToList)
    }
    BackHandler { onBackPressed() }
  }
}

@Composable
fun SearchResults(
  searchUiState: SearchUiState,
  onAddToList: (Book) -> Unit
) {
  LazyColumn {
    items(searchUiState.searchResult) { book ->
      BookRow(book, showAddToList = true, onAddToList = onAddToList)
    }
  }
}
