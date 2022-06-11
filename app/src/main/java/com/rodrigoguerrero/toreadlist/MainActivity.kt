package com.rodrigoguerrero.toreadlist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rodrigoguerrero.toreadlist.models.SearchUiState
import com.rodrigoguerrero.toreadlist.ui.components.BookListScreen
import com.rodrigoguerrero.toreadlist.ui.components.SearchScreen
import com.rodrigoguerrero.toreadlist.ui.theme.ToReadListTheme
import com.rodrigoguerrero.toreadlist.viewmodels.BookListViewModel
import com.rodrigoguerrero.toreadlist.viewmodels.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  private val searchViewModel: SearchViewModel by viewModels()
  private val bookListViewModel: BookListViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

      setContent {
        val navController = rememberNavController()

        ToReadListTheme {
          NavHost(navController = navController, startDestination = "booklist") {
            composable("booklist") {
              val books by bookListViewModel.bookList.collectAsState(emptyList())
              bookListViewModel.getBookList()
              BookListScreen(navController, books)
            }
            composable("search") {
              val searchUiState by searchViewModel.searchUiState.collectAsState(SearchUiState())
              SearchScreen(
                searchUiState = searchUiState,
                onSearch = { searchViewModel.search(it) },
                onAddToList = { searchViewModel.addToList(it) }
              )
            }
          }
        }
      }
    }
}
