package com.apolis.groceryapplication1.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.apolis.groceryapplication1.R

class SplashActivity : AppCompatActivity() {

    // This is the loading time of the splash screen
    private val SPLASH_TIME_OUT:Long = 3000
    private val isLoggedIn = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
    }

    private fun init() {
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            // This method will be executed once the timer is over
            // Start your app main activity

            //checkLogin()
            startActivity(Intent(this, HomeActivity::class.java))

            // close this activity
            finish()
        }, SPLASH_TIME_OUT)
    }

//    fun checkLogin(){
//        var intent = if(isLoggedIn){
//            // user id logged in
//            Intent(this, CategoryActivity::class.java)
//        }else{
//            Intent(this, RegisterActivity::class.java)
//        }
//        startActivity(intent)
//        finish()
//    }
}