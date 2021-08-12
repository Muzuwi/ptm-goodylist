package com.teambear.goodielist

import androidx.appcompat.app.AppCompatActivity
import android.annotation.SuppressLint
import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.MotionEvent
import android.view.View
import android.view.WindowInsets
import android.widget.LinearLayout
import android.widget.TextView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.teambear.goodielist.databinding.ActivityLogInPageBinding
import com.teambear.goodielist.databinding.MainActivityBinding
import com.teambear.goodielist.network.GoodieAPIWorker
import com.teambear.goodielist.network.User
import com.teambear.goodielist.network.UserAccount
import com.teambear.goodielist.storage.LocalRecipes
import kotlinx.coroutines.launch
import java.util.*

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class LogInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityLogInPageBinding.inflate(layoutInflater)
        val context = this

        setContentView(binding.root)

        binding.logInButton.setOnClickListener {
            val username = binding.logInUsername.text.toString()
            val password = binding.logInPassword.text.toString()

            lifecycleScope.launch{
                binding.logInUsername.isEnabled = false
                binding.logInPassword.isEnabled = false
                val token = GoodieAPIWorker.LoginUser(username, password)

                if(token != null){  //Logged In
                    UserAccount.SetLocalUser(
                        User(
                            username,
                            token
                        )
                    )
                    //Go to main activity
                    val intent = Intent(context, MainActivity::class.java)
                    finishAffinity()
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(context).toBundle())
                } else {
                    Snackbar.make(
                        binding.root.rootView,
                        "Invalid username or password",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
                binding.logInUsername.isEnabled = true
                binding.logInPassword.isEnabled = true
            }
        }

        binding.logInToRegisterButton.setOnClickListener {
            val intent = Intent(context, RegisterActivity::class.java)
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(context).toBundle())
        }

    }

}