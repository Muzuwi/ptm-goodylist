package com.teambear.goodielist.network

import android.content.Context
import android.content.SharedPreferences
import java.lang.IllegalArgumentException
import java.util.*

object UserAccount {
    private var localUser: User? = null
    private lateinit var appContext: Context

    private fun ResetUserPrefs(prefs: SharedPreferences) {
        val edit = prefs.edit()

        edit.remove("token")
        edit.remove("username")
        edit.apply()
    }

    fun Init(context: Context) {
        appContext = context
        val prefs = appContext.getSharedPreferences("userData", Context.MODE_PRIVATE) ?: return

        if(!prefs.contains("username")) {
            ResetUserPrefs(prefs)
            return
        }

        val username = prefs.getString("username", null)!!
        val tokenString = prefs.getString("token", null)
        var token: UUID? = null
        if(tokenString != null) {
            try {
                token = UUID.fromString(tokenString)
            } catch (ex: IllegalArgumentException) {
                token = null
            }
        }

        localUser = User(username, token)
    }

    fun SetLocalUser(user: User): Boolean {
        val prefs = appContext.getSharedPreferences("userData", Context.MODE_PRIVATE) ?: return false
        val edit = prefs.edit()

        edit.putString("username", user.username)
        edit.putString("token", user.token.toString())
        edit.apply()

        localUser = user

        return true
    }

    fun ClearLocalUser() {
        val prefs = appContext.getSharedPreferences("userData", Context.MODE_PRIVATE) ?: return
        val edit = prefs.edit()

        edit.putString("username", null)
        edit.putString("token", null)
        edit.apply()

        localUser = null
    }

    fun GetLocalUser(): User? {
        return localUser
    }

}