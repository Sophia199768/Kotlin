package com.example.api

import com.example.dto.News
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.engine.cio.*
import kotlinx.serialization.*

@Serializable
data class NewsResponse(
    val results: List<News>
)

suspend fun getNews(count: Int = 100): List<News> {
    val client = HttpClient(CIO)
    try {
        val response: NewsResponse = client.get("https://kudago.com/public-api/v1.4/news/") {
            parameter("page_size", count)
            parameter("order_by", "publication_date")
            parameter("location", "spb")
        }.body()

        return response.results
    } finally {
        client.close()
    }
}
