package com.rodrigoguerrero.toreadlist.network.models

import com.google.gson.annotations.SerializedName

data class SearchDTO(
  @SerializedName("docs")
  val books: List<BookDTO>
)
