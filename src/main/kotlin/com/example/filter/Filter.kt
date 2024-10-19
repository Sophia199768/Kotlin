import com.example.dto.News
import java.time.*

fun List<News>.getMostRatedNews(count: Int, period: ClosedRange<LocalDate>): List<News> {
    return this.filter { news ->
        news.publicationDate in period
    }.sortedByDescending { it.rating }
        .take(count)
}
