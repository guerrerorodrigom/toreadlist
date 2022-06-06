package com.rodrigoguerrero.toreadlist.network.service

import com.rodrigoguerrero.toreadlist.network.models.SearchDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BookService {
  @GET("search.json")
  suspend fun search(@Query("q") query: String): Response<SearchDTO>
}
