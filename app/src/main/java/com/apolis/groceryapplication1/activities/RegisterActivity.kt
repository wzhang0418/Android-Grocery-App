package com.apolis.groceryapplication1.activities

import android.content.Intent
import android.os.Bundle
<<<<<<< HEAD
import android.util.Log
import android.view.MenuItem
import android.view.View
=======
import android.view.MenuItem
>>>>>>> 9d8b7eebba08273004465e73c702a9d3b1a4effa
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.apolis.groceryapplication1.R
import com.apolis.groceryapplication1.app.Endpoints
<<<<<<< HEAD
import com.apolis.groceryapplication1.helpers.*
=======
import com.apolis.groceryapplication1.helpers.SessionManager
import com.apolis.groceryapplication1.helpers.toast
>>>>>>> 9d8b7eebba08273004465e73c702a9d3b1a4effa
import com.apolis.groceryapplication1.models.RegisterResponse
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.app_bar.*
import org.json.JSONObject

class RegisterActivity : AppCompatActivity() {

    private lateinit var sessionManager: SessionManager
<<<<<<< HEAD
    var view: View? = null
=======
>>>>>>> 9d8b7eebba08273004465e73c702a9d3b1a4effa

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        sessionManager = SessionManager(this)

        init()
    }

    private fun init() {

        //Set up tool bar
        var toolbar = tool_bar
        toolbar.title = "Register"
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //click "register" button
        button_register.setOnClickListener {
            if(isValidInput()) {
                var firstName = edit_text_name_register.text.toString()
                var email = edit_text_email_register.text.toString()
                var password = edit_text_password_register.text.toString()
                var mobile = edit_text_mobile_register.text.toString()

<<<<<<< HEAD
                register( firstName, email, password, mobile)
=======
                register(null, firstName, email, password, mobile)
>>>>>>> 9d8b7eebba08273004465e73c702a9d3b1a4effa
            }
        }

        //Click "click here" text
        text_view_click_here_to_login.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
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

    //Send the register request info to server API
<<<<<<< HEAD
    fun register( firstName: String, email: String, password: String, mobile: String) {

        var params = HashMap<String, String>()

=======
    fun register(_id: Int?, firstName: String, email: String, password: String, mobile: String) {

        var params = HashMap<String, String>()
        params["_id"] = _id.toString()
>>>>>>> 9d8b7eebba08273004465e73c702a9d3b1a4effa
        params["firstName"] = firstName
        params["email"] = email
        params["password"] = password
        params["mobile"] = mobile

        val jsonObject = JSONObject(params as Map<*, *>)

        val url = Endpoints.getRegisterURL()

        var request = JsonObjectRequest(Request.Method.POST, url, jsonObject,
                Response.Listener {
                    var gson = GsonBuilder().create()
                    var registerResponse = gson.fromJson(it.toString(), RegisterResponse::class.java)
                    if(registerResponse.error){
<<<<<<< HEAD
                        this.toast("Register Failed")
                    }else{
                        this.toast("Register successfully")
                        sessionManager.register(registerResponse.data)
                        startActivity(Intent(this, CategoryActivity::class.java))
                    }
                },
                Response.ErrorListener {
                    //this.toast(it.message.toString())
                    this.toast("Register failed")
=======
                        this.toast("Login Failed")
                    }else{
                        this.toast("Register successfully")
                        sessionManager.register(registerResponse.data)
                        startActivity(Intent(this, LoginActivity::class.java))
                    }
                },
                Response.ErrorListener {
                    this.toast(it.message.toString())
>>>>>>> 9d8b7eebba08273004465e73c702a9d3b1a4effa
                })
        Volley.newRequestQueue(this).add(request)
    }

    //Check if the input is valid, if not, toast each of the error message
    private fun isValidInput(): Boolean {
<<<<<<< HEAD

        view = View(this)

=======
>>>>>>> 9d8b7eebba08273004465e73c702a9d3b1a4effa
        if((edit_text_name_register.text.isNullOrEmpty())
                ||(edit_text_email_register.text.isNullOrEmpty())
                ||(edit_text_password_register.text.isNullOrEmpty())
                ||(edit_text_mobile_register.text.isNullOrEmpty())
        ){
            if(edit_text_name_register.text.isNullOrEmpty()) {
                this.toast("Name cannot be empty")
            }
            if(edit_text_email_register.text.isNullOrEmpty()) {
                this.toast("Email cannot be empty")
            }
            if(edit_text_password_register.text.isNullOrEmpty()){
                this.toast("Password cannot be empty")
            }
            if(edit_text_mobile_register.text.isNullOrEmpty()) {
<<<<<<< HEAD
                this.toast("Mobile number cannot be empty")
=======
                this.toast("Mobile cannot be empty")
>>>>>>> 9d8b7eebba08273004465e73c702a9d3b1a4effa
            }
            return false
        }
        return true
    }

}

