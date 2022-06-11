package com.rodrigoguerrero.toreadlist.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.rodrigoguerrero.toreadlist.LocalPaddings
import com.rodrigoguerrero.toreadlist.R
import com.rodrigoguerrero.toreadlist.models.Book
import com.rodrigoguerrero.toreadlist.ui.theme.MyReadingTheme

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
      .padding(all = LocalPaddings.current.small),
    elevation = 12.dp,
    shape = RoundedCornerShape(size = 11.dp)
  ) {
    Row(
      modifier = Modifier
        .padding(LocalPaddings.current.medium)
    ) {
      AsyncImage(
        modifier = Modifier
          .width(120.dp)
          .padding(end = LocalPaddings.current.small),
        model = ImageRequest
          .Builder(LocalContext.current)
          .data(book.coverUrl)
          .error(LocalContext.current.getDrawable(R.drawable.error_cover))
          .build(),
        contentScale = ContentScale.Crop,
        contentDescription = book.title
      )
      Column {
        Text(text = book.title, style = MyReadingTheme.typography.H5)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = book.author, style = MyReadingTheme.typography.subtitle)
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