package com.apolis.groceryapplication1.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.apolis.groceryapplication1.R
import com.apolis.groceryapplication1.app.Endpoints
import com.apolis.groceryapplication1.helpers.SessionManager
import com.apolis.groceryapplication1.helpers.SessionManager.Companion.FILE_NAME
import com.apolis.groceryapplication1.helpers.SessionManager.Companion.KEY_EMAIL
import com.apolis.groceryapplication1.helpers.SessionManager.Companion.KEY_PASSWORD
import com.apolis.groceryapplication1.helpers.toast
import com.apolis.groceryapplication1.models.LoginFail
import com.apolis.groceryapplication1.models.LoginSuccess
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.app_bar.*
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {

    val FILE_NAME = "my_file"
    val KEY_NAME = "name"
    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sharedPreferences = getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
        sessionManager = SessionManager(this)

        init()
    }

    private fun init() {

        //Set up tool bar
        var toolbar = tool_bar
        toolbar.title = "Login"
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        var savedEmail = sharedPreferences.getString(KEY_EMAIL,null)
        edit_text_email_login.setText(savedEmail)

        button_login.setOnClickListener {
            if(isValidInput()){
                var email = edit_text_email_login.text.toString()
                var password = edit_text_password_login.text.toString()
                login(email,password)
            }
        }
        text_view_click_here_to_register.setOnClickListener {
            startActivity((Intent(this, RegisterActivity::class.java)))
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return true
    }

    private fun isValidInput(): Boolean {
        if((edit_text_email_login.text.isNullOrEmpty()) || (edit_text_password_login.text.isNullOrEmpty())){
            if(edit_text_email_login.text.isNullOrEmpty()) {
                this.toast("Email cannot be empty")
            }
            if(edit_text_password_login.text.isNullOrEmpty()){
                this.toast("Password cannot be empty")
            }
            return false
        }
        return true
    }

    private fun login(email: String, password: String){
        val params = HashMap<String, String>()
        params["email"] = edit_text_email_login.text.toString()
        params["password"] = edit_text_password_login.text.toString()

        val jsonObject = JSONObject(params as Map<*, *>)

        val url = Endpoints.getLoginURL()

        var request = JsonObjectRequest(Request.Method.POST, url, jsonObject,
                Response.Listener {
                    var gson = GsonBuilder().create()
                    var loginSuccess = gson.fromJson(it.toString(), LoginSuccess::class.java)
                    var loginFail = gson.fromJson(it.toString(), LoginFail::class.java)
                    //mContext.toast(loginResponse.toString())
                    if(!loginFail.error){
                        this.toast("Login Successfully")
                        sessionManager.saveLoginData(email,password)
                        this.toast("data saved in share preference file")
                        startActivity(Intent(this, CategoryActivity::class.java))
                    }else{
                        this.toast("Login failed")
                    }
                },
                Response.ErrorListener {
                    this.toast(it.message.toString())
                }
        )
        Volley.newRequestQueue(this).add(request)
    }
}