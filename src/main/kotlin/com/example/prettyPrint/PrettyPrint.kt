package com.example.prettyPrint

class PrettyPrint {
    @DslMarker
    annotation class PrettyPrintDsl

    @PrettyPrintDsl
    class ReadmeBuilder {
        private val content = StringBuilder()

        fun header(level: Int, block: () -> String) {
            content.append("#".repeat(level)).append(" ").append(block()).append("\n\n")
        }

        fun text(block: () -> String) {
            content.append(block()).append("\n\n")
        }

        fun code(language: String, block: () -> String) {
            content.append("```").append(language).append("\n")
            content.append(block()).append("\n")
            content.append("```\n\n")
        }

        fun build(): String = content.toString()
    }

    fun readme(block: ReadmeBuilder.() -> Unit): String {
        val builder = ReadmeBuilder()
        builder.block()
        return builder.build()
    }

    fun bold(text: String) = "**$text**"
    fun underlined(text: String) = "**$text**"
    fun link(link: String, text: String) = "[$text]($link)"
}


fun main() {
    val prettyPrint = PrettyPrint()

    val result = prettyPrint.readme {
        header(level = 1) { "Kotlin Lecture" }
        header(level = 2) { "DSL" }

        text {
            "Today we will try to recreate ${prettyPrint.bold("DSL")} from this article: ${prettyPrint.link(link = "https://kotlinlang.org/docs/type-safe-builders.html", text = "Kotlin Docs")}!!!" +
                    " It is so ${prettyPrint.underlined("fascinating and interesting")}!"
        }

        code(language = "kotlin") {
            """
                fun main() {
                    println("Hello world!")
                }
            """.trimIndent()
        }
    }

    println(result)
}
