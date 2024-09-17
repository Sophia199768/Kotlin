import com.example.dto.News
import kotlin.math.exp
import java.time.*

fun List<News>.getMostRatedNews(count: Int, period: ClosedRange<LocalDate>): List<News> {
    return this.filter { news ->
        val newsDate = LocalDate.parse(news.siteUrl.substringAfterLast("/")) // Предполагается, что дата где-то в URL
        newsDate in period
    }.onEach { news ->
        news.rating = calculateRating(news)
    }.sortedByDescending { it.rating }
        .take(count)
}

fun calculateRating(news: News): Double {
    return 1 / (1 + exp(-(news.favoritesCount.toDouble() / (news.commentsCount + 1))))
}