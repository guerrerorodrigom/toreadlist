package com.rodrigoguerrero.toreadlist.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.rodrigoguerrero.toreadlist.models.Book

@Composable
fun BookListScreen(
  navController: NavController,
  books: List<Book>
) {
  Scaffold(
    floatingActionButton = {
      FloatingActionButton(onClick = { navController.navigate("search") }) {
        Icon(
          imageVector = Icons.Filled.Search,
          contentDescription = "Search"
        )
      }
    },
    floatingActionButtonPosition = FabPosition.End
  ) {
    LazyColumn {
      items(books) { book ->
        BookRow(book, showAddToList = false)
      }
    }
  }
}