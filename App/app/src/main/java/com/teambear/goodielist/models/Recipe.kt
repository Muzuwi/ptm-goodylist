package com.teambear.goodielist.models

import java.util.*

data class Recipe (
    var id: UUID,

    var username: String,

    var created: Long,

    var name: String,

    var tags: List<String>,

    var ingredients: List<String>,

    var description: String,

    var steps: List<String>
)
