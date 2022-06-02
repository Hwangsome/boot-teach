package com.bh.kotlin.arrow.core2


import arrow.core.Validated
import arrow.core.ValidatedNel
import arrow.core.computations.ResultEffect.bind
import arrow.core.invalidNel
import arrow.core.validNel
import com.bh.kotlin.arrow.core.Organizer
import java.time.LocalDate

inline class Title(val value: String)
inline class Description(val value: String)
data class ValidateEventError(val reason: String)
data class Event(
    val id: EventId,
    val organizer: Organizer,
    val title: Title,
    val description: Description,
    val date: LocalDate
)

inline class EventId(val value: Long){
    companion object {
        fun create(value: Long): ValidatedNel<ValidateEventError, EventId> =
            if(value <0) ValidateEventError("EventId needs to be bigger than 0, but found $value.").invalidNel()
            else EventId(value).validNel()
    }
}



fun main() {
    println(Validated.Valid(EventId(value=-1)).bind())
}