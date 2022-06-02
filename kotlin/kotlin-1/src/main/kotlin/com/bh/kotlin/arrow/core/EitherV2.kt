package com.bh.kotlin.arrow.core

import arrow.core.Either
import arrow.core.computations.either
import kotlinx.coroutines.runBlocking
import java.time.LocalDate


inline class Title(val value: String)
inline class Description(val value: String)

data class Event(
    val id: EventId,
    val organizer: Organizer,
    val date: LocalDate
)
data class ValidationError(val reason: String)

inline class EventId(val value: Long) {
    companion object {
        fun create(value: Long): Either<ValidationError, EventId> =
            if (value > 0) Either.Right(EventId(value))
            else Either.Left(ValidationError("EventId needs to be bigger than 0, but found $value."))
    }
}

inline class Organizer(val value: String) {
    companion object {
        /* Same implementation for Title and Description */
        fun create(value: String): Either<ValidationError, Organizer> = when {
            value.isEmpty() -> Either.Left(ValidationError("Organizer cannot be empty"))
            value.isBlank() -> Either.Left(ValidationError("Organizer cannot be blank"))
            else -> Either.Right(Organizer(value))
        }
    }
}

suspend fun generateId(): Long =
    100L

suspend fun createEvent(): Either<ValidationError, Event> =
    either {
        /**
         * suspend fun <A> Kind<F, A>.bind(): A
         */
        val id = EventId.create(generateId()).bind()
        val organizer = Organizer.create("").bind()
        Event(id, organizer, LocalDate.now())
    } // Left(ValidationError("EventId needs to be bigger than 0, but found -1."))

/**
 * 由于EventId 验证失败，either 块立即返回 Left，而不是等到调用者调用 bind() 方法时才返回。
 *
 * 总结：
 * Either 短路，Validated累积错误
 */
fun main(): Unit = runBlocking{



    val result =createEvent()
    println(result)





}