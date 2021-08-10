package com.teambear.goodielist.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.teambear.goodielist.network.apitypes.UserLogin
import com.teambear.goodielist.network.apitypes.UserToken
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.POST
import java.util.*

interface GoodieRESTInterface {
    @POST("auth/login")
    suspend fun Login(@Body userLogin: UserLogin): UserToken?

    @POST("auth/logout")
    suspend fun Logout(@Body token: UUID): Boolean
}


private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory(MediaType.parse("application/json")!!))
    .baseUrl("https://goodielist.muzuwi.dev/api/v1/")
    .build()


object GoodieREST {
    val service: GoodieRESTInterface by lazy {
        retrofit.create(GoodieRESTInterface::class.java)
    }
}


