package com.apolis.groceryapplication1.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.apolis.groceryapplication1.R
import com.apolis.groceryapplication1.adapters.AdapterAddress
import com.apolis.groceryapplication1.adapters.AdapterCategory
import com.apolis.groceryapplication1.app.Endpoints
import com.apolis.groceryapplication1.helpers.toast
import com.apolis.groceryapplication1.models.Address
import com.apolis.groceryapplication1.models.AddressResponse
import com.apolis.groceryapplication1.models.Category
import com.apolis.groceryapplication1.models.CategoryResponse
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_address.*
import kotlinx.android.synthetic.main.activity_category.*
import kotlinx.android.synthetic.main.app_bar.*
import kotlinx.android.synthetic.main.row_address_adapter.view.*

class AddressActivity : AppCompatActivity() {

    var addressList: ArrayList<Address> = ArrayList()
    lateinit var adapterAddress: AdapterAddress
    lateinit var address: Address

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address)

        init()
    }

    private fun init() {
        //Set up customized toolbar
        var toolbar = tool_bar
        toolbar.title = "Manage Address"
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //Get address data
        getAddress()

        //Add data to linear recycler view
        adapterAddress = AdapterAddress(this,addressList)
        recycler_view_address_linear.layoutManager = LinearLayoutManager(this)
        recycler_view_address_linear.adapter = adapterAddress

        button_add_new_address.setOnClickListener {
            //navigate to AddAddress Activity
            startActivity(Intent(this, AddAddressActivity::class.java))
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

    //Get data from API
    private fun getAddress() {
        address = Address()
        val url = Endpoints.getAddressURL()+"/5f114569487f410017816626"
        //val url = Endpoints.getAddressURL()+"/"+address.userId
        var requestQueue = Volley.newRequestQueue(this)
        var request = StringRequest(
                Request.Method.GET, url,
                Response.Listener {
                    var gson = GsonBuilder().create()
                    var addressResponse = gson.fromJson(it, AddressResponse::class.java)
                    adapterAddress.setData(addressResponse.address)
                    this.toast("successfully get the address")
                },
                Response.ErrorListener {
                    this.toast("Error in getting address")
                    Log.d("ABC",it.message.toString())
                }
        )
        requestQueue.add(request)
    }

    //Edit address data from API
    fun editAddress() {
        val url = Endpoints.getAddressURL()
        var requestQueue = Volley.newRequestQueue(this)
        var request = StringRequest(
                Request.Method.DELETE, url,
                Response.Listener {
//                    var gson = GsonBuilder().create()
//                    var addressResponse = gson.fromJson(it, AddressResponse::class.java)
//                    adapterAddress.setData(addressResponse.address)
                    this.toast("successfully deleted the address")
                },
                Response.ErrorListener {
                    this.toast("Error in deleting address")
                    Log.d("ABC",it.message.toString())
                }
        )
        requestQueue.add(request)
    }
}