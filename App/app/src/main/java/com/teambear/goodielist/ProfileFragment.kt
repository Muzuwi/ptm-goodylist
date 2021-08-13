package com.teambear.goodielist

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.teambear.goodielist.network.UserAccount
import com.teambear.goodielist.storage.LocalRecipes

class ProfileFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)



        return view
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val localUser = UserAccount.GetLocalUser()

        with(localUser){
            //TODO: Można do bazy dodać takie pierdółki jak emial, data utworzenia konta (nie mam dostępu?) czy id hardcodowanego awatara
            view.findViewById<TextView>(R.id.profileUsername).text = this?.username ?: "Not logged in"
            view.findViewById<TextView>(R.id.profileToken).text = "Token: ${this?.token ?: "no token"}"
            view.findViewById<TextView>(R.id.profileRecipesSaved).text = "Recipes saved: ${LocalRecipes.GetRecipeCount()}"
        }

    }
}