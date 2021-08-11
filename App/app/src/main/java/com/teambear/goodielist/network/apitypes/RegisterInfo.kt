package com.teambear.goodielist.network.apitypes

import kotlinx.serialization.Serializable

@Serializable
data class RegisterInfo (
    val username: String,
    val password: String
)
