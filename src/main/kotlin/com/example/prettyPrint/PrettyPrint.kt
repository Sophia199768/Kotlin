package com.example.prettyPrint

class PrettyPrint {
    private val content = StringBuilder()

    fun header(level: Int, init: Header.() -> Unit) {
        val header = Header(level).apply(init)
        content.append(header.toString()).append("\n")
    }

    fun text(init: Text.() -> Unit) {
        val text = Text().apply(init)
        content.append(text.toString()).append("\n")
    }

    override fun toString(): String = content.toString()
}

class Header(private val level: Int) {
    private val content = StringBuilder()

    operator fun String.unaryPlus() {
        content.append(this)
    }

    override fun toString(): String {
        return "#".repeat(level) + " " + content.toString()
    }
}

class Text {
    private val content = StringBuilder()

    operator fun String.unaryPlus() {
        content.append(this)
    }

    fun bold(text: String) {
        content.append("**").append(text).append("**")
    }

    fun underlined(text: String) {
        content.append("__").append(text).append("__")
    }

    fun code(language: ProgrammingLanguage, init: Code.() -> Unit) {
        val code = Code(language).apply(init)
        content.append(code.toString())
    }

    fun link(link: String, text: String) {
        content.append("[$text]($link)")
    }

    override fun toString(): String = content.toString()
}

class Code(private val language: ProgrammingLanguage) {
    private val content = StringBuilder()

    operator fun String.unaryPlus() {
        content.append(this)
    }

    override fun toString(): String {
        return "```${language.name}\n${content.toString()}\n```"
    }
}

enum class ProgrammingLanguage {
    KOTLIN, JAVA, PYTHON
}

fun prettyPrint(init: PrettyPrint.() -> Unit): PrettyPrint {
    return PrettyPrint().apply(init)
}


fun main() {
    val pretty = prettyPrint {
        header(level = 1) { +"Kotlin Lecture" }
        header(level = 2) { +"DSL" }

        text {
            +("Today we will try to recreate ${bold("DSL")} from this article: ${link(link = "https://kotlinlang.org/docs/type-safe-builders.html", text = "Kotlin Docs")}!!!")
            +("It is so ${underlined("fascinating and interesting")}!")
            code(language = ProgrammingLanguage.KOTLIN) {
                +"""
                    fun main() {
                        println("Hello world!")
                    }
                """.trimIndent()
            }
        }
    }

    println(pretty)
}
