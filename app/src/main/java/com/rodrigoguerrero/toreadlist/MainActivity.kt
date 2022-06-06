package com.rodrigoguerrero.toreadlist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.rodrigoguerrero.toreadlist.models.SearchUiState
import com.rodrigoguerrero.toreadlist.ui.components.SearchScreen
import com.rodrigoguerrero.toreadlist.ui.theme.ToReadListTheme
import com.rodrigoguerrero.toreadlist.viewmodels.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  private val viewModel: SearchViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

      setContent {
          val searchUiState by viewModel.searchUiState.collectAsState(SearchUiState())

          ToReadListTheme {
            SearchScreen(
              searchUiState = searchUiState,
              onSearch = { viewModel.search(it) }
            )
        }
      }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ToReadListTheme {
        Greeting("Android")
    }
}