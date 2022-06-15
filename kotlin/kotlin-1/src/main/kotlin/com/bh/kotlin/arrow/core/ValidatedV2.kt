package com.bh.kotlin.arrow.core

import arrow.core.Either
import arrow.core.NonEmptyList
import arrow.core.Valid
import arrow.core.Validated
import arrow.core.ValidatedNel
import arrow.core.continuations.Effect
import arrow.core.continuations.effect
import arrow.core.continuations.either
import arrow.core.invalidNel
import arrow.core.valid
import arrow.core.validNel
import arrow.core.zip
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.fail
import java.time.LocalDate

/**
 * https://www.47deg.com/blog/functional-domain-modeling-part-2/
 *
Either当我们只对第一个错误 或成功值感兴趣时使用。
Validated当我们对所有错误 或成功值感兴趣时使用。
使用traverse返回或map_ Iterable_EitherValidated
用于parTraverse并行映射Iterable返回Eitheror Validated。
 */
data class Event2(
    val id: EventId2,
    val organizer: Organizer2,
    val title2: Title2,
    val date: LocalDate
)

//
inline class EventId2(val value: Long) {
    companion object {
        fun create(value: Long): ValidatedNel<ValidationError, EventId2> =
            if (value > 0) Valid(EventId2(value))
            else ValidationError("EventId2 needs to be bigger than 0, but found $value.").invalidNel()
    }
}


inline class Title2(val title: String) {
    companion object {
        fun create(title: String): ValidatedNel<ValidationError, Title2> =
            if (title.isNotEmpty()) Valid(Title2(title))
            else ValidationError("Title2 needs to be bigger than 0, but found $title.").invalidNel()
    }
}

//
inline class Organizer2(val value: String) {
    companion object {
        fun create(value: String): ValidatedNel<ValidationError, Organizer2> = when {
            value.isEmpty() -> ValidationError("Organizer2 cannot be empty").invalidNel()
            value.isBlank() -> ValidationError("Organizer2 cannot be blank").invalidNel()
            else -> Valid(Organizer2(value))
        }
    }

}

suspend fun date(): LocalDate =
    LocalDate.now()

suspend fun createEvent2(): ValidatedNel<ValidationError, Event2> =
    EventId2.create(generateId())
        .zip(
            Organizer2.create("1"),
            Title2.create("1")
        ) { id, organizer, title ->
            Event2(id, organizer, title, date())
        }

/**
 * 我们现在可以使用either块构建一个Event，either允许我们抽取，当我们遇到Left的时候，他会立即返回Left的结果值
 * 这个行为通常叫做短路行为，因为either计算块短路当遇到了Left的时候
 */
suspend fun createEventAndLog2(): Either<NonEmptyList<ValidationError>, Unit> =
    either {

        // ValidatedNel<ValidationError, Event2>
        val event = createEvent2().bind()
        println(event)
    }


/**
 * 因为 验证EventId2 失败，either 块立即为EventId2返回Left
 * 所以我们不知道 Title、Organizer 也不正确
 * 因为Either短路与第一次遇到Left。
 *
 * 假设你在填写一个表单，第一次报告用户名错误，修改之后，再说密码错误，这是不友好的。
 * 友好的：同时输出 你用户名和密码都输入错误了。所以这时候我们可以使用Validated
 *
 */
//suspend fun createEvent3(): Either<ValidationError, Event2> =
//    either {
//        val id = EventId2.create(generateId()).bind()
//        val title = Title2.create("                ").bind()
//        val organizer = Organizer2.create("").bind()
//        Event2(id, title, organizer, LocalDate.now())
//    }


/**
 * 我们的基于 Either 的代码和基于 Validated 的代码之间几乎没有什么变化。
 *
 * OR 关系和两者都有两种情况：Left/Invalid 和 Right/Valid。
 *
 * 正如您可能已经猜到的那样，两者之间的区别在于，Either 在 Left 短路，而 Validated 在 Invalid 中累积错误。
 * 因此，如果我们尝试使用 Validated 类型而不是 Either 来构造 Event，我们将能够找出在构造 Event 时发生的所有错误
 * 为此，我们使用了 ValidatedNel，即 typealias ValidatedNel<E, A> = Validated<NonEmptyList<E>, A>。
 * 这允许我们将所有错误累积到 NonEmptyList 中。
 * 使用 NonEmptyList 而不是 List 更精确地建模，因为总会有至少一个 ValidationError；否则我们会期待一个有效事件。
 */
inline class EventId3(val value: Long) {
    companion object {
        fun create(value: Long): ValidatedNel<ValidationError, EventId3> =
            if (value > 0) EventId3(value).valid()
            else ValidationError("EventId3 needs to be bigger than 0, but found $value.").invalidNel()
    }
}

inline class Organizer3(val value: String) {
    companion object {
        fun create(value: String): ValidatedNel<ValidationError, Organizer3> = when {
            value.isEmpty() -> ValidationError("Organizer3 cannot be empty").invalidNel()
            value.isBlank() -> ValidationError("Organizer3 cannot be blank").invalidNel()
            else -> Valid(Organizer3(value))
        }
    }
}

/**
 * 在结果值中，我们找到了在尝试构造事件时发生的所有累积错误。
 * 委托我们的调用zip允许我们结合独立的价值观。为了组合这些值，
 * 我们还需要提供一种组合Invalid案例的方法。
 * 这里我们Semigroup.nonEmptyList()默认使用ValidatedNel.
 * Semigroup是一个定义如何combine关联的功能接口。在 的情况下NonEmptyList，
 * 它只是委托给NonEmptyList#plus，这会导致所有元素的非空列表。
 *
 * 这里的zip没有传递Semigroup 的时候使用的是 nonEmptyList
public inline fun <E, A, B, C, Z> ValidatedNel<E, A>.zip(
b: ValidatedNel<E, B>,
c: ValidatedNel<E, C>,
f: (A, B, C) -> Z
): ValidatedNel<E, Z> =
zip(Semigroup.nonEmptyList(), b, c, f)
 */
suspend fun createEvent4(): ValidatedNel<ValidationError, Event2> =
    EventId2.create(generateId()).zip(
        Organizer2.create(""),
        Title2.create("")
    ) { id, organizer,title  -> Event2(id, organizer, title, date()) }



data class User(val id: Long, val name: String)


fun createUser1(id: Long, name: String): Either<ValidationError, User> {
    val id = if(id > 0) Either.Right(id) else Either.Left(ValidationError("Id of name: $name needs to be bigger than 0, but found $id."))
    val name = if (name.isNotBlank()) Either.Right(name) else Either.Left(ValidationError("Name of id: $id cannot be blank"))
//    return id.zip(name){
//        id,name ->User(id,name)
//    }
    return id.zip(name,::User)
}


fun Iterable<Pair<Long, String>>.createUsers1(): List<Either<ValidationError, User>> =
    map { (id, name) -> createUser1(id, name) }



fun main(): Unit = runBlocking {


    /**
     *
    public inline fun <E, A, B, C, D, Z> arrow.core.ValidatedNel<E, A> /* = arrow.core.Validated<arrow.core.Nel<E> /* = arrow.core.NonEmptyList<E> */, A> */.zip(b: arrow.core.ValidatedNel<E, B> /* = arrow.core.Validated<arrow.core.Nel<E> /* = arrow.core.NonEmptyList<E> */, B> */, c: arrow.core.ValidatedNel<E, C> /* = arrow.core.Validated<arrow.core.Nel<E> /* = arrow.core.NonEmptyList<E> */, C> */, d: arrow.core.ValidatedNel<E, D> /* = arrow.core.Validated<arrow.core.Nel<E> /* = arrow.core.NonEmptyList<E> */, D> */, f: (A, B, C, D) -> Z): arrow.core.ValidatedNel<E, Z>
     */
    /**
     *
    public inline fun <E, A, B, C, D, Z> arrow.core.ValidatedNel<E, A> /* = arrow.core.Validated<arrow.core.Nel<E> /* = arrow.core.NonEmptyList<E> */, A> */.zip(b: arrow.core.ValidatedNel<E, B> /* = arrow.core.Validated<arrow.core.Nel<E> /* = arrow.core.NonEmptyList<E> */, B> */, c: arrow.core.ValidatedNel<E, C> /* = arrow.core.Validated<arrow.core.Nel<E> /* = arrow.core.NonEmptyList<E> */, C> */, d: arrow.core.ValidatedNel<E, D> /* = arrow.core.Validated<arrow.core.Nel<E> /* = arrow.core.NonEmptyList<E> */, D> */, f: (A, B, C, D) -> Z): arrow.core.ValidatedNel<E, Z>
     */
    val result = createEvent2()
    println("result $result")

    val list1 = listOf(1, 2, 3)
    val list2 = listOf(1, 2, 3)
//    zipList:[(1, 1), (2, 2), (3, 3)]
    val zipList = list1 zip list2
//    zipList:[1=>1, 2=>2, 3=>3]
    val zipList2 = list1.zip(list2) { a, b ->
        "$a=>$b"
    }
    println("zipList:$zipList2")

    createEventAndLog2()

//    val validated = Validated.Valid(40)
//    val a = effect<String, Int> {
//        val x: Int = validated.bind()
//        println("x:$x")
//        x
//    }.toValidated()
//    println("a:$a")

    val createEvent2 = createEvent2()
    println("createEvent2:$createEvent2")


    val createUser1 =createUser1(1,"1")
    println("createUser1:$createUser1")

    val result2 = EventId2.create(1).zip(
        Organizer2.create("1"),
        Title2.create("1"),
    ){
        eventId2, organizer2,title2 ->
        Event2(eventId2,organizer2,title2,date())
    }
    println("result2 = ${result2}")
}

