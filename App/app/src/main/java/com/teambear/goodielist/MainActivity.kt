package com.teambear.goodielist

import android.os.Bundle
import android.os.ParcelUuid
import android.view.Menu
import android.view.View
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.teambear.goodielist.databinding.MainActivityBinding
import com.teambear.goodielist.network.GoodieREST
import com.teambear.goodielist.network.User
import com.teambear.goodielist.network.UserAccount
import com.teambear.goodielist.network.apitypes.UserLogin
import com.teambear.goodielist.storage.LocalRecipes
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.Exception
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: MainActivityBinding

    private fun TempTestUser() {
        var user = UserAccount.GetLocalUser()
        if(user != null) {
            System.out.println("User is not null. Username: " + user.username)
            if(user.token != null)
                System.out.println("Token: " + user.token.toString())
        } else {
            System.out.println("User is null")
            UserAccount.SetLocalUser(
                User(
                    "test",
                    UUID.randomUUID()
                )
            )
        }

        lifecycleScope.launch {
            try {
                Snackbar.make(binding.navView, "Trying to log in..", Snackbar.LENGTH_SHORT).show()
                var result = GoodieREST.service.Login(UserLogin("test", "string"))
                Snackbar.make(binding.navView, "Logged in. Token=" + result?.token.toString(), Snackbar.LENGTH_SHORT).show()

            } catch (ex: Exception) {
                Snackbar.make(binding.navView, ex.message.toString(), Snackbar.LENGTH_SHORT).show()
                println(ex.message)
            }
            println("What?")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.appBarMain.toolbar)

        UserAccount.Init(applicationContext)
        LocalRecipes.Init(applicationContext)

        TempTestUser()

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.recipeListFragment
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    fun AddRecipeButtonClicked(view: View) {
        val nav = findNavController(R.id.nav_host_fragment_content_main)
        nav.navigate(R.id.recipeListFragment_to_fragmentRecipeEdit);
    }

}