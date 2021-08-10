package com.teambear.goodielist.network.apitypes

import com.teambear.goodielist.serializers.UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class UserToken (
    @Serializable(with = UUIDSerializer::class)
    val token: UUID,
)