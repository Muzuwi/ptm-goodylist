package com.teambear.goodielist.network.apitypes

import kotlinx.serialization.Serializable

@Serializable
data class UserLogin(
    var username: String,
    var password: String,
)
