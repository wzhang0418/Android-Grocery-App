package com.apolis.groceryapplication1.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.apolis.groceryapplication1.R
import com.apolis.groceryapplication1.adapters.AdapterCart
import com.apolis.groceryapplication1.database.DBHelper
import com.apolis.groceryapplication1.models.Product
import com.custom.sliderimage.logic.SliderImage
import kotlinx.android.synthetic.main.activity_cart_detail.*
import kotlinx.android.synthetic.main.activity_category.*
import kotlinx.android.synthetic.main.app_bar.*
import kotlinx.android.synthetic.main.row_cart_detail_adapter.view.*

class CartDetailActivity : AppCompatActivity() {

    lateinit var mlist: ArrayList<Product>
    lateinit var product: Product
    lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart_detail)

        dbHelper = DBHelper(this)
//        product = Product()

        init()
    }

    private fun init() {

        //Set up customized toolbar
        var toolbar = tool_bar
        toolbar.title = "Cart"
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //Display items of recycler view
        mlist = dbHelper.readCart()
        recycler_view.layoutManager = LinearLayoutManager(this)
        var adapterCart = AdapterCart(this, mlist)
        recycler_view.adapter = adapterCart

        //Navigate to checkout page
        button_checkout.setOnClickListener {
            startActivity(Intent(this, AddressActivity::class.java))
        }

    }

    //When click on cart icon on the Menu bar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return true
    }
}