package com.bh.kotlin.arrow.core2

import arrow.core.Either
import arrow.core.continuations.Effect
import arrow.core.continuations.effect
import arrow.core.continuations.ensureNotNull

import kotlinx.coroutines.runBlocking
import java.io.File
import java.io.FileNotFoundException

@JvmInline
value class Content(val body: List<String>)

sealed interface FileError

@JvmInline
value class SecurityError(val msg: String?) : FileError

@JvmInline
value class FileNotFound(val path: String) : FileError
object EmptyPath : FileError {
    override fun toString() = "EmptyPath"
}

fun readFile(path: String): Effect<EmptyPath, Unit> = effect {
    if (path.isEmpty()) shift(EmptyPath) else Unit
}

fun readFile2(path: String?): Effect<EmptyPath, Unit> = effect<EmptyPath, Unit> {
    ensureNotNull(path) { EmptyPath }
    ensure(path.isEmpty()) { EmptyPath }
}

fun readFile3(path: String?): Effect<FileError, Content> = effect<FileError, Content> {
    ensureNotNull(path) { EmptyPath }
    ensure(path.isNotEmpty()) { EmptyPath }
    try {
        val lines = File(path).readLines()
        Content(lines)
    } catch (e: FileNotFoundException) {
        shift(FileNotFound(path))
    } catch (e: SecurityException) {
        shift(SecurityError(e.message))
    }
}

fun readFile4(path: String?): Effect<FileError, Content> = effect<FileError, Content> {
    ensureNotNull(path) { EmptyPath }
    ensure(path.isNotEmpty()) { EmptyPath }
    try {
        val lines = File(path).readLines()
        println("lines:$lines")
        Content(lines)
    } catch (e: FileNotFoundException) {
        println("FileNotFoundException:$e")
        shift(FileNotFound(path))
    } catch (e: SecurityException) {
        println("SecurityException:$e")
        shift(SecurityError(e.message))
    }

}
// .fold(
//    {fileError:FileError ->fileError},
//    {Content:Content ->Content()}
//)


fun main() = runBlocking {
    val res = effect<String, Int> {
        shift("Hello, World!")
    }.fold({ str: String -> str.length }, { int -> int })
    println("res:$res")


    val shift = effect<FileError, Content> {
        ensureNotNull("q") { EmptyPath }
        ensure("q".isNotEmpty()) { EmptyPath }
        Content(listOf("2", "3"))
    }.fold({ str: FileError -> str }, { content -> content })
    println("shift:$shift")


    val failed: Effect<String, Int> =
        effect { shift("failed") }

    val resolved: Effect<Nothing, Int> =
        failed.handleError { it.length }
    println("resolved:$resolved")


}




