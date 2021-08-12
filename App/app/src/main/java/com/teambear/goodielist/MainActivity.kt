package com.teambear.goodielist

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.os.ParcelUuid
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.TextView
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
import com.teambear.goodielist.models.Recipe
import com.teambear.goodielist.models.RecipeCategory
import com.teambear.goodielist.network.GoodieAPIWorker
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
//        var user = UserAccount.GetLocalUser()
//        if(user != null) {
//            System.out.println("User is not null. Username: " + user.username)
//            if(user.token != null)
//                System.out.println("Token: " + user.token.toString())
//        } else {
//            System.out.println("User is null")
//            UserAccount.SetLocalUser(
//                User(
//                    "test",
//                    UUID.randomUUID()
//                )
//            )
//        }
//
//        lifecycleScope.launch {
//            try {
//                Snackbar.make(binding.navView, "Trying to log in..", Snackbar.LENGTH_SHORT).show()
//                var result = GoodieREST.service.Login(UserLogin("test", "string"))
//                Snackbar.make(binding.navView, "Logged in. Token=" + result?.token.toString(), Snackbar.LENGTH_SHORT).show()
//            } catch (ex: Exception) {
//                Snackbar.make(binding.navView, ex.message.toString(), Snackbar.LENGTH_SHORT).show()
//                println(ex.message)
//            }
//            println("What?")
//        }
//
//        lifecycleScope.launch {
//            try {
//                var worker = GoodieAPIWorker()
//                var recipe = worker.FetchRecipe(UUID.randomUUID(), UUID.randomUUID())
//
//            } catch (ex: Exception) {
//                Snackbar.make(binding.navView, ex.message.toString(), Snackbar.LENGTH_SHORT).show()
//                println(ex.message)
//            }
//        }
//        lifecycleScope.launch {
//            try {
//                var worker = GoodieAPIWorker()
//                var new = Recipe(UUID.randomUUID(),"",Calendar.getInstance().time, "", RecipeCategory.LUNCH, listOf(), listOf(), "", listOf())
//                var recipe = worker.UpdateRecipe(UUID.randomUUID(), new)
//
//            } catch (ex: Exception) {
//                Snackbar.make(binding.navView, ex.message.toString(), Snackbar.LENGTH_SHORT).show()
//                println(ex.message)
//            }
//        }
//        lifecycleScope.launch {
//            try {
//                var worker = GoodieAPIWorker()
//                var new = Recipe(UUID.randomUUID(),"",Calendar.getInstance().time, "", RecipeCategory.LUNCH, listOf(), listOf(), "", listOf())
//                var recipe = worker.CreateRecipe(UUID.randomUUID(), new)
//
//            } catch (ex: Exception) {
//                Snackbar.make(binding.navView, ex.message.toString(), Snackbar.LENGTH_SHORT).show()
//                println(ex.message)
//            }
//        }
//        lifecycleScope.launch {
//            try {
//                var worker = GoodieAPIWorker()
//                var recipes = worker.FetchUserRecipes(UUID.randomUUID())
//            } catch (ex: Exception) {
//                Snackbar.make(binding.navView, ex.message.toString(), Snackbar.LENGTH_SHORT).show()
//                println(ex.message)
//            }
//        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        UserAccount.Init(applicationContext)

        if(UserAccount.GetLocalUser() == null){
            //Go to login page
            val intent = Intent(this, LogInActivity::class.java)
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
            finish()
        }

        LocalRecipes.Init(applicationContext)

        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.appBarMain.toolbar)

        val localUser = UserAccount.GetLocalUser()
        var logInString = "Not Logged In"

        if(localUser != null){
            logInString = "Logged in as ${localUser.username}"
        }
        binding.navView.getHeaderView(0).findViewById<TextView>(R.id.main_nav_logged_as).text = logInString

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.recipeListFragment, R.id.recipeOnlineListFragment
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