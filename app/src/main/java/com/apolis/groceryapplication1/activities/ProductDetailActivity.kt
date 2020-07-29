package com.apolis.groceryapplication1.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuItemCompat
import com.apolis.groceryapplication1.R
import com.apolis.groceryapplication1.app.Endpoints
import com.apolis.groceryapplication1.database.DBHelper
import com.apolis.groceryapplication1.helpers.toast
import com.apolis.groceryapplication1.models.Product
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_product_detail.*
import kotlinx.android.synthetic.main.activity_product_detail.view.*
import kotlinx.android.synthetic.main.app_bar.*
import kotlinx.android.synthetic.main.menu_badge_icon.view.*

class ProductDetailActivity : AppCompatActivity() {

    lateinit var dbHelper: DBHelper

    var textViewCount: TextView? = null
    var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        dbHelper = DBHelper(this)

        init()
    }

    private fun init() {
        var toolbar = tool_bar
        toolbar.title = "Products"
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        var product = intent.getSerializableExtra(Product.PRODUCT_KEY) as Product
        text_view_product_name_detail.text = product.productName
        text_view_product_price_detail.text = "$" + product.price.toString()
        text_view_product_description_detail.text = product.description

        Picasso.get()
                .load(Endpoints.getImage() + product.image)
                .placeholder(R.drawable.place_holder)
                .error(R.drawable.no_image)
                .into(card_view_detail.image_view_detail)

        button_add_to_cart.setOnClickListener {
            product.qty = 1
            dbHelper.addToCart(product)
            startActivity(Intent(this, CartDetailActivity::class.java))
        }
        button_buy_now.setOnClickListener {
            startActivity(Intent(this, AddressActivity::class.java))
        }
    }

    //Cart icon event
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
        var count = dbHelper.getCartQuantity().toInt()
        if (count == 0)
            textViewCount?.visibility = View.GONE
        else {
            textViewCount?.visibility = View.VISIBLE
            textViewCount?.text = count.toString()
        }
    }

    //Press back button on menu bar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
            R.id.action_cart -> {
                this.toast("cart icon clicked")
                startActivity(Intent(this, CartDetailActivity::class.java))
            }
        }
        return true
    }

}