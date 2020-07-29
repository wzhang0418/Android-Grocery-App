package com.apolis.groceryapplication1.activities

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.apolis.groceryapplication1.R
import com.apolis.groceryapplication1.database.DBHelper
import com.apolis.groceryapplication1.helpers.SessionManager
import com.apolis.groceryapplication1.helpers.SessionManager.Companion.FILE_NAME
import com.apolis.groceryapplication1.helpers.SessionManager.Companion.KEY_NAME
import com.apolis.groceryapplication1.helpers.snackBarCallBack
import com.apolis.groceryapplication1.helpers.toast
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.message_box.view.*
import kotlinx.android.synthetic.main.nav_header.view.*

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    lateinit var sharedPreferences: SharedPreferences
    lateinit var sessionManager: SessionManager
    lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        init()
    }

    private fun init() {
        sharedPreferences = getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        sessionManager = SessionManager(this)
        dbHelper = DBHelper(this)

        //Create tool bar
        var toolbar = tool_bar
        toolbar.title = "Home"
        setSupportActionBar(toolbar)

        //Initialize drawerlayout
        drawerLayout = drawer_layout
        navView = nav_view
        var headView = navView.getHeaderView(0)
        headView.text_view_user_name.text = sharedPreferences.getString(KEY_NAME,null)

        var toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar,0, 0)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)

        button_login_home.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
        text_view_register_home.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.item_account -> {
                this.toast("item_account clicked")
                startActivity(Intent(this, MyAccountActivity::class.java))
            }
            R.id.item_order -> {
                startActivity(Intent(this, OrderHistoryActivity::class.java))
            }
            R.id.item_logout -> {
                showMessageBox("xxx")
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START)
        else
            super.onBackPressed()
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