package com.teambear.goodielist.network

import com.teambear.goodielist.interfaces.IGoodieAPIWorker
import java.util.*

class GoodieAPIWorker : IGoodieAPIWorker {
    override suspend fun RegisterUser(username: String, password: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun LoginUser(username: String, password: String): UUID {
        TODO("Not yet implemented")
    }

    override suspend fun LoginUser(token: UUID): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun Logout(token: UUID) {
        TODO("Not yet implemented")
    }
}