package com.apolis.groceryapplication1.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.get
import androidx.drawerlayout.widget.DrawerLayout
import com.apolis.groceryapplication1.R
import com.apolis.groceryapplication1.helpers.toast
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar.*
import kotlinx.android.synthetic.main.nav_header.view.*

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        init()
    }

    private fun init() {
        //Create tool bar
        var toolbar = tool_bar
        toolbar.title = "Home"
        setSupportActionBar(toolbar)

        //Initialize drawerlayout
        drawerLayout = drawer_layout
        navView = nav_view
        var headView = navView.getHeaderView(0)
        headView.text_view_user_name.text = "Wenzhao Zhang"

        val toogle = ActionBarDrawerToggle(this, drawerLayout, toolbar, 0, 0)
        toogle.syncState()
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
                this.toast("My Account")
                //TODO: Navigate to MyAccount Activity
            }
            //TODO: Implement the click event for other items
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
}