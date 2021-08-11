package com.teambear.goodielist

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.teambear.goodielist.databinding.ActivityLogInPageBinding
import com.teambear.goodielist.databinding.ActivityRegisterPageBinding
import com.teambear.goodielist.network.GoodieAPIWorker
import com.teambear.goodielist.network.User
import com.teambear.goodielist.network.UserAccount
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityRegisterPageBinding.inflate(layoutInflater)
        val context = this

        setContentView(binding.root)

        binding.registerButton.setOnClickListener {
            val username = binding.registerUsername.text.toString()
            val password = binding.registerPassword.text.toString()
            val passwordConfirm = binding.registerPasswordConfirm.text.toString()

            if(password != passwordConfirm){
                return@setOnClickListener
            }

            lifecycleScope.launch{

                if(GoodieAPIWorker.RegisterUser(username, password))
                {
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
                        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(context).toBundle())
                    }
                    else{
                        val intent = Intent(context, LogInActivity::class.java)
                        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(context).toBundle())
                    }
                }
            }
        }

        binding.registerToLogInButton.setOnClickListener {
            val intent = Intent(context, LogInActivity::class.java)
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(context).toBundle())
        }

    }

}