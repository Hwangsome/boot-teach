package com.bh.utils

import io.micrometer.core.instrument.Tag

object Utils {
    fun extractTags(tagKeyValues: HashMap<String, String>) =
        tagKeyValues.toList().map { Tag.of(it.first, it.second) }
}
