import com.example.dto.News
import com.github.doyaaaaaken.kotlincsv.dsl.csvWriter

import java.nio.file.Files
import java.nio.file.Paths

fun saveNews(path: String, news: Collection<News>) {
    val filePath = Paths.get(path)

    if (Files.exists(filePath)) {
        throw IllegalArgumentException("File exists")
    }

    csvWriter().open(path) {
        writeRow("id", "publicationDate", "title", "place", "description", "siteUrl", "favoritesCount", "commentsCount")
        news.forEach { item ->
            writeRow(
                item.id,
                item.publicationDate,
                item.title,
                item.place,
                item.description,
                item.siteUrl,
                item.favoritesCount,
                item.commentsCount
            )
        }
    }
}
