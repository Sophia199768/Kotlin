import com.example.dto.News

import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

fun saveNews(path: String, news: Collection<News>) {
    val filePath = Paths.get(path)

    if (Files.exists(filePath)) {
        throw IllegalArgumentException("File exists")
    }

    File(path).bufferedWriter().use { writer ->
        writer.write("id,publicationDate,title,place,description,siteUrl,favoritesCount,commentsCount\n")
        news.forEach {
            writer.write("${it.id},${it.publicationDate},${it.title},${it.place},${it.description},${it.siteUrl},${it.favoritesCount},${it.commentsCount}\n")
        }
    }
}
