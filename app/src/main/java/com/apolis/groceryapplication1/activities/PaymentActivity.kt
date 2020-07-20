package com.apolis.groceryapplication1.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.apolis.groceryapplication1.R
import com.apolis.groceryapplication1.helpers.toast
import kotlinx.android.synthetic.main.activity_payment.*
import kotlinx.android.synthetic.main.app_bar.*

class PaymentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        init()
    }

    private fun init() {
        //Set up customized toolbar
        var toolbar = tool_bar
        toolbar.title = "Manage Address"
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        button_pay_online.setOnClickListener {
            this.toast("Online Payment Is Not Available")
        }
        button_pay_cash.setOnClickListener {
            startActivity(Intent(this, OrderConfirmationActivity::class.java))
        }
        button_apply_coupon.setOnClickListener {
            this.toast("Coupons Are Not Available")
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