package com.teambear.goodielist.interfaces

import java.util.*

interface IGoodieAPIWorker {
    suspend fun RegisterUser(username: String, password: String): Boolean

    suspend fun LoginUser(username: String, password: String): UUID

    suspend fun LoginUser(token: UUID): Boolean

    suspend fun Logout(token: UUID)

}