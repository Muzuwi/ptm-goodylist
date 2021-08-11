package com.teambear.goodielist.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.teambear.goodielist.models.Recipe
import com.teambear.goodielist.network.apitypes.DbRecipe
import com.teambear.goodielist.network.apitypes.RegisterInfo
import com.teambear.goodielist.network.apitypes.UserLogin
import com.teambear.goodielist.network.apitypes.UserToken
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit
import retrofit2.http.*
import java.util.*

interface GoodieRESTInterface {
    @POST("auth/register")
    suspend fun Register(@Body registerInfo: RegisterInfo)

    @POST("auth/login")
    suspend fun Login(@Body userLogin: UserLogin): UserToken

    @POST("auth/validate")
    suspend fun Validate(@Body token: UUID)

    @POST("auth/logout")
    suspend fun Logout(@Body token: UUID)

    @GET("recipes/{id}")
    suspend fun FetchRecipe(
        @Header("X-User-Token") token: UUID,
        @Path("id") id: UUID
    ): DbRecipe

    @POST("recipes/{id}")
    suspend fun CreateRecipe(
        @Header("X-User-Token") token: UUID,
        @Path("id") id: UUID,
        @Body recipe: DbRecipe
    )

    @PUT("recipes/{id}")
    suspend fun UpdateRecipe(
        @Header("X-User-Token") token: UUID,
        @Path("id") id: UUID,
        @Body recipe: DbRecipe
    )

    @GET("user/recipes")
    suspend fun FetchUserRecipes(
        @Header("X-User-Token") token: UUID
    ): List<DbRecipe>

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


