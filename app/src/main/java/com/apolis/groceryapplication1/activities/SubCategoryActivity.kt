package com.apolis.groceryapplication1.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuItemCompat
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.apolis.groceryapplication1.R
import com.apolis.groceryapplication1.adapters.AdapterFragments
import com.apolis.groceryapplication1.app.Endpoints
import com.apolis.groceryapplication1.database.DBHelper
import com.apolis.groceryapplication1.helpers.toast
import com.apolis.groceryapplication1.models.Category
import com.apolis.groceryapplication1.models.SubCategoryResponse
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_cart_detail.*
import kotlinx.android.synthetic.main.activity_sub_category.*
import kotlinx.android.synthetic.main.app_bar.*
import kotlinx.android.synthetic.main.menu_badge_icon.view.*
import kotlinx.android.synthetic.main.row_cart_detail_adapter.*

class SubCategoryActivity : AppCompatActivity() {

    lateinit var adapterFragments: AdapterFragments
    lateinit var dbHelper: DBHelper
    var catId = 0

    var textViewCount: TextView? = null
    var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub_category)

        catId = intent.getIntExtra(Category.CATEGORY_KEY, 0)
        init()
    }

    private fun init() {

        var toolbar = tool_bar
        toolbar.title = "Sub Category"
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        getData(catId)
    }

    private fun getData(catId: Int) {
        val requestQueue = Volley.newRequestQueue(this)
        val request = StringRequest(
            Request.Method.GET,
            Endpoints.getSubCategoryByCatId(catId),
            Response.Listener {
                val gson = GsonBuilder().create()
                val subCategoryResponse = gson.fromJson(it, SubCategoryResponse::class.java)

                adapterFragments = AdapterFragments(supportFragmentManager)

                adapterFragments.addFragment(subCategoryResponse.subCategoryData)

                view_pager.adapter = adapterFragments

                tab_layout.setupWithViewPager(view_pager)
                //this.toast("tab layout setup with view pager")
            },
            Response.ErrorListener {
                Log.d("B", it.message.toString())
            }
        )
        requestQueue.add(request)
    }

    // Cart icon event
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_cart_icon, menu)

        var item = menu.findItem(R.id.action_cart)
        MenuItemCompat.setActionView(item, R.layout.menu_badge_icon)
        var view = MenuItemCompat.getActionView(item)
        textViewCount = view.text_view_cart_count
        updateCartCount()
        view.setOnClickListener {
            this.toast("Cart Icon Clicked")
            startActivity(Intent(this, CartDetailActivity::class.java))
        }

        return true
    }
    private fun updateCartCount() {
        dbHelper = DBHelper(this)
        this.toast(dbHelper.getCartQuantity().toString())
        var count = dbHelper.getCartQuantity().toInt()
        if (count == 0)
            textViewCount?.visibility = View.GONE
        else {
            textViewCount?.visibility = VISIBLE
            textViewCount?.text = count.toString()
        }
    }

    //Press back button on menu bar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                finish()//destroy the current activity to go back to the previous activity
            }
        }
        return true
    }
}