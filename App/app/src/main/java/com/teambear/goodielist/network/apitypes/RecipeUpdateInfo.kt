package com.teambear.goodielist.network.apitypes

import kotlinx.serialization.Serializable

@Serializable
data class RecipeUpdateInfo(
    val created: Long,
    val json: String
)
