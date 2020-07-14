package com.apolis.groceryapplication1.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.apolis.groceryapplication1.R
import com.apolis.groceryapplication1.app.Endpoints
import com.apolis.groceryapplication1.helpers.toast
import com.apolis.groceryapplication1.models.Product
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_product_detail.*
import kotlinx.android.synthetic.main.activity_product_detail.view.*

class ProductDetailActivity : AppCompatActivity() {

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_demo, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val menuBar = findViewById<View>(R.id.action_save_menu_demo)
        menuBar.setOnClickListener {
            this.toast("menu bar clicked")
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        supportActionBar!!.title = ""

        var product = intent.getSerializableExtra(Product.PRODUCT_KEY) as Product
        text_view_product_name_detail.text = product.productName
        text_view_product_price_detail.text = "$" + product.price.toString()
        text_view_product_description_detail.text = product.description

        Picasso.get()
            .load(Endpoints.getImage() + product.image)
            .placeholder(R.drawable.place_holder)
            .error(R.drawable.no_image)
            .into(card_view_detail.image_view_detail)
    }
}