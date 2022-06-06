package com.rodrigoguerrero.toreadlist.network.models

import com.google.gson.annotations.SerializedName

data class BookDTO(
  val title: String,
  @SerializedName("cover_i")
  val coverId: Int,
  @SerializedName("author_name")
  val authors: List<String>? = emptyList()
)
