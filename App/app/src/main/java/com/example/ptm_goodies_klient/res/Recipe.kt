package com.example.ptm_goodies_klient.res

import android.accounts.AuthenticatorDescription
import android.graphics.Picture

data class Recipe(
    var id: Int,
    var name: String,
    var category: String,
    var ingredients: List<String>,
    var description: String
) {

}