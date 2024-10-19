package com.example.dto

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDate
import kotlin.math.exp

@Serializable
data class News(
    val id: Int,
    @Contextual @SerialName("publication_date") val publicationDate: LocalDate,
    val title: String?,
    val place: Place?,
    val description: String?,
    @SerialName("site_url") val siteUrl: String,
    @SerialName("favorites_count") val favoritesCount: Int,
    @SerialName("comments_count") val commentsCount: Int,
)
{
    val rating: Double
        get() = 1.0 / (1 + exp(-(favoritesCount / (commentsCount + 1).toDouble())))
}
