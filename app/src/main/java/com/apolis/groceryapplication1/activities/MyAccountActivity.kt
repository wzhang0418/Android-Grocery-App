package com.apolis.groceryapplication1.activities

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.apolis.groceryapplication1.R
import com.apolis.groceryapplication1.database.DBHelper
import com.apolis.groceryapplication1.helpers.SessionManager
import com.apolis.groceryapplication1.helpers.SessionManager.Companion.FILE_NAME
import com.apolis.groceryapplication1.helpers.SessionManager.Companion.KEY_EMAIL
import com.apolis.groceryapplication1.helpers.SessionManager.Companion.KEY_MOBILE
import com.apolis.groceryapplication1.helpers.SessionManager.Companion.KEY_NAME
import com.apolis.groceryapplication1.helpers.toast
import kotlinx.android.synthetic.main.activity_my_account.*
import kotlinx.android.synthetic.main.app_bar.*
import kotlinx.android.synthetic.main.message_box.view.*

class MyAccountActivity : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences
    lateinit var sessionManager: SessionManager
    lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_account)

        sharedPreferences = getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        sessionManager = SessionManager(this)
        dbHelper = DBHelper(this)

        init()

    }

    private fun init() {

        //Set up menu bar
        var toolbar = tool_bar
        toolbar.title = "My Account"
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        text_view_account_name.text = sharedPreferences.getString(KEY_NAME, null)
        text_view_account_email.text = sharedPreferences.getString(KEY_EMAIL, null)
        text_view_account_mobile.text = sharedPreferences.getString(KEY_MOBILE, null)

        button_logout.setOnClickListener {
            showMessageBox("xxx")
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                finish()//destroy the current activity to go back to the previous activity
            }
        }
        return true
    }

    fun showMessageBox(text: String){

        //Inflate the dialog as custom view
        val messageBoxView = LayoutInflater.from(this).inflate(R.layout.message_box, null)

        //AlertDialogBuilder
        val messageBoxBuilder = AlertDialog.Builder(this).setView(messageBoxView)

        //show dialog
        val  messageBoxInstance = messageBoxBuilder.show()

        //set Listener
        messageBoxView.button_yes.setOnClickListener(){
            sessionManager.logout()
            this.toast("Logout successfully")
            dbHelper.clearCartContent()
            startActivity(Intent(this, RegisterActivity::class.java))
        }
        messageBoxView.button_no.setOnClickListener(){
            //close dialog
            messageBoxInstance.dismiss()
        }
    }
}