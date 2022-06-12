package com.rodrigoguerrero.toreadlist.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rodrigoguerrero.toreadlist.models.Book

@Composable
fun BookRow(
  book: Book,
  modifier: Modifier = Modifier,
  showAddToList: Boolean = false,
  onAddToList: (Book) -> Unit = { }
) {
  Card(
    modifier = modifier
      .fillMaxWidth()
      .padding(all = 8.dp),
    elevation = 12.dp,
    shape = RoundedCornerShape(size = 11.dp)
  ) {
    Row(
      modifier = Modifier
        .padding(16.dp)
    ) {
      Column {
        Text(text = book.title, style = MaterialTheme.typography.h5)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = book.author, style = MaterialTheme.typography.subtitle1)
        Spacer(modifier = Modifier.height(4.dp))

        if (showAddToList) {
          Button(
            onClick = { onAddToList(book) },
            modifier = Modifier.fillMaxWidth()
          ) {
            Text(text = "Add to List")
          }
        }
      }
    }
  }
}

@Preview
@Composable
fun PreviewBookRow() {
  BookRow(
    Book(author = "Author", title = "title", coverId = 1234)
  )
}