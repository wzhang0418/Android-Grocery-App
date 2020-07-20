package com.apolis.groceryapplication1.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.apolis.groceryapplication1.R
import com.apolis.groceryapplication1.app.Endpoints
import com.apolis.groceryapplication1.helpers.SessionManager
import com.apolis.groceryapplication1.helpers.toast
import com.apolis.groceryapplication1.models.RegisterResponse
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_register.*
import org.json.JSONObject

class RegisterActivity : AppCompatActivity() {

    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        sessionManager = SessionManager(this)

        init()
    }

    private fun init() {
        //click "register" button
        button_register.setOnClickListener {
            if(isValidInput()) {
                var firstName = edit_text_name_register.text.toString()
                var email = edit_text_email_register.text.toString()
                var password = edit_text_password_register.text.toString()
                var mobile = edit_text_mobile_register.text.toString()

                register(firstName, email, password, mobile)
            }
        }

        //Click "click here" text
        text_view_click_here_to_login.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    //Send the register request info to server API
    fun register(firstName: String, email: String, password: String, mobile: String) {

        var params = HashMap<String, String>()
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
                        this.toast("Login Failed")
                    }else{
                        this.toast("Register successfully")
                        sessionManager.register(firstName,email,password,mobile)
                        startActivity(Intent(this, LoginActivity::class.java))
                    }
                },
                Response.ErrorListener {
                    this.toast(it.message.toString())
                })
        Volley.newRequestQueue(this).add(request)
    }

    //Check if the input is valid, if not, toast each of the error message
    private fun isValidInput(): Boolean {
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
                this.toast("Mobile cannot be empty")
            }
            return false
        }
        return true
    }

}

