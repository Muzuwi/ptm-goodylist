package com.teambear.goodielist.network.apitypes

import com.teambear.goodielist.serializers.UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class DbRecipe(
    @Serializable(with = UUIDSerializer::class)
    val id: UUID,

    val username: String,

    val json: String
)
