package com.example.api

import DateSerializer
import com.example.dto.News
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.contextual

@Serializable
data class Response(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<News>
)

suspend fun getNews(count: Int = 100): List<News> {
    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                serializersModule = SerializersModule {
                    contextual(DateSerializer)
                }
            })
        }
    }

    try {
        val response: Response = client.get("https://kudago.com/public-api/v1.4/news/") {
            parameter("page_size", count)
            parameter("fields", "id,publication_date,title,place,description,site_url,favorites_count,comments_count")
            parameter("order_by", "publication_date")
            parameter("location", "spb")
        }.body()

        return response.results
    } finally {
        client.close()
    }
}
