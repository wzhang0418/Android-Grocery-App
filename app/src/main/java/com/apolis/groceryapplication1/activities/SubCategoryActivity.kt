package com.apolis.groceryapplication1.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
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
import kotlinx.android.synthetic.main.activity_sub_category.*
import kotlinx.android.synthetic.main.app_bar.*

class SubCategoryActivity : AppCompatActivity() {

    lateinit var adapterFragments: AdapterFragments
    lateinit var dbHelper: DBHelper
    var catId = 0

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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_cart_icon, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                finish()//destroy the current activity to go back to the previous activity
            }
            R.id.action_cart -> {
                this.toast("cart icon clicked")
                startActivity(Intent(this, CartDetailActivity::class.java))
            }
        }
        return true
    }
}