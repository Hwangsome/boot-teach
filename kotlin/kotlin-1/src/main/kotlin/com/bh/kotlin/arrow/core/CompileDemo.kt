package com.bh.kotlin.arrow.core

import arrow.core.Validated
import arrow.core.invalid
import arrow.core.nonFatalOrThrow
import arrow.core.valid

public inline fun <A> catch(f: () -> A): Validated<Throwable, A> =
    try {
        f().valid()
    } catch (e: Throwable) {
        e.nonFatalOrThrow().invalid()
    }