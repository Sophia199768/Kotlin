package com.example.dto

import kotlinx.serialization.Serializable

@Serializable
data class News(
    val id: Int,
    val title: String,
    val place: String,
    val description: String,
    val siteUrl: String,
    val favoritesCount: Int,
    val commentsCount: Int,
    var rating: Double = 0.0
)
