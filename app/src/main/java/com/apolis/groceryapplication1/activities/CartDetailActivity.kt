package com.apolis.groceryapplication1.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.apolis.groceryapplication1.R
import com.apolis.groceryapplication1.adapters.AdapterCart
import com.apolis.groceryapplication1.database.DBHelper
import com.apolis.groceryapplication1.models.Product
import kotlinx.android.synthetic.main.activity_cart_detail.*
import kotlinx.android.synthetic.main.app_bar.*
import java.math.BigDecimal
import java.math.RoundingMode

class CartDetailActivity : AppCompatActivity() {

    lateinit var mlist: ArrayList<Product>
    lateinit var product: Product
    lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart_detail)

        dbHelper = DBHelper(this)

        scroll_view_cart_detail.visibility = GONE
        relative_layout_total.visibility = GONE
        button_checkout.visibility = GONE

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
        recycler_view_cart.layoutManager = LinearLayoutManager(this)
        var adapterCart = AdapterCart(this, mlist)
        recycler_view_cart.adapter = adapterCart
        if(!mlist.isNullOrEmpty()){
            scroll_view_cart_detail.visibility = VISIBLE
            image_view_empty_cart.visibility = GONE
            text_view_empty_cart.visibility = GONE
            relative_layout_total.visibility = VISIBLE
            button_checkout.visibility = VISIBLE
            button_go_shopping.visibility = GONE
        }

        //Press checkout and navigate to checkout page
        button_checkout.setOnClickListener {
            startActivity(Intent(this, AddressActivity::class.java))
        }
        button_go_shopping.setOnClickListener {
            startActivity(Intent(this, CategoryActivity::class.java))
        }

        //Get total summary
        getTotal()

    }

    private fun getTotal(){
        var total = dbHelper.getTotal()
        text_view_sub_total_price.text = "$" + BigDecimal(total[0]).setScale(2, RoundingMode.HALF_EVEN).toString()
        text_view_discount_price.text = "-$" + BigDecimal(total[1]).setScale(2, RoundingMode.HALF_EVEN).toString()
        text_view_delivery_charges_price.text = "$" + BigDecimal(total[2]).setScale(2, RoundingMode.HALF_EVEN).toString()
        text_view_order_amount_price.text = "$" + BigDecimal(total[3]).setScale(2, RoundingMode.HALF_EVEN).toString()
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