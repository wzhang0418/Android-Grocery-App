package com.apolis.groceryapplication1.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.apolis.groceryapplication1.R
import kotlinx.android.synthetic.main.activity_order_confirmation.*

class OrderConfirmationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_confirmation)

        init()
    }

    private fun init() {
        button_go_back_to_home.setOnClickListener {
            startActivity(Intent(this, CategoryActivity::class.java))
        }
    }
}