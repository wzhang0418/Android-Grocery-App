package com.apolis.groceryapplication1.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.apolis.groceryapplication1.R
import com.apolis.groceryapplication1.app.Endpoints
import com.apolis.groceryapplication1.database.DBHelper
import com.apolis.groceryapplication1.helpers.SessionManager
import com.apolis.groceryapplication1.helpers.toast
import com.apolis.groceryapplication1.models.Address
import com.apolis.groceryapplication1.models.OrderSummary
import com.apolis.groceryapplication1.models.Product
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_add_address.*
import kotlinx.android.synthetic.main.activity_payment.*
import kotlinx.android.synthetic.main.app_bar.*
import org.json.JSONObject

class PaymentActivity : AppCompatActivity() {

    lateinit var dbHelper: DBHelper
    lateinit var address: Address
    lateinit var sessionManager: SessionManager
    var productList: ArrayList<Product> = ArrayList()

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
            postOrder()
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

    //POST the orders to server
    private fun postOrder(){
        sessionManager = SessionManager(this)
        var userId = sessionManager.getUserId().toString()

        dbHelper = DBHelper(this)
        productList = dbHelper.readCart()
        //orderSummary = dbHelper.getOrderSummary()

        // not sure
        var product:ArrayList<Any> = ArrayList()
        var oneProduct= HashMap<String, Any>()

        //map prouctList into jsonObject
        for (item in productList) {
            oneProduct["mrp"] = item.mrp.toString()
            oneProduct["price"] = item.price.toString()
            oneProduct["quantity"] = item.qty
            oneProduct["productName"] = item.productName.toString()
            val jsonObjectProduct = JSONObject(oneProduct as Map<String, Any?>)
            product.add(jsonObjectProduct)
        }

        //map address into jsonObject
        var shipAddress = HashMap<String, Any?>()
        address = Address()
        shipAddress["houseNo"] = address.houseNo
        shipAddress["streeName"] = address.streetName
        shipAddress["city"] = address.city
        shipAddress["pincode"] = address.pincode
        val jsonAddress = JSONObject(shipAddress as Map<String, Any?>)

//        //map orderSummary into jsonObject
//        var orderSum = HashMap<String, Int>()
//        orderSum["deliveryCharges"] = orderSummary.deliveryCharges
//        orderSum["discount"] = orderSummary.discount
//        orderSum["orderAmount"] = orderSummary.orderAmount
//        orderSum["ourPrice"] = orderSummary.ourPrice
//        val jsonPayment = JSONObject(orderSum as Map<, >)


        var params = HashMap<String, Any>()
        //params["orderSummary"] = jsonPayment
//        params["userId"] = userId
//        params["products"] = product
//        params["address"] = jsonAddress
        params["userId"] = "5f114569487f410017816626"
        params["products"] = product
        params["address"] = jsonAddress


        val jsonObject = JSONObject(params as Map<String, Any?>)
        val url = Endpoints.getOrdersURL()

        var requestQueue = Volley.newRequestQueue(this)
        var request = JsonObjectRequest(
                Request.Method.POST, url, jsonObject,
                Response.Listener {
                    var gson = GsonBuilder().create()
                    var addressUploadRespone = gson.fromJson(it.toString(), OrderSummary::class.java)
                    startActivity(Intent(this, OrderConfirmationActivity::class.java))
                },
                Response.ErrorListener {
                    toast("Error in posting order")
                })

        requestQueue.add(request)

    }
}