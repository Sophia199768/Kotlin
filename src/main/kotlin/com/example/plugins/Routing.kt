package com.example.plugins

import com.example.api.getNews
import getMostRatedNews
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import saveNews
import java.time.LocalDate

fun Application.configureRouting() {
    routing {
        get("/") {
            saveNews("test.txt", getNews())
        }

        get("/news") {
            val news = getNews()
            call.respondText(news.toString())
        }

        get("/rated") {
            val news = getNews().getMostRatedNews(3, LocalDate.of(1990,10,10)..LocalDate.now())
            call.respondText(news.toString())
        }


    }
}
