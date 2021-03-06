package com.apolis.groceryapplication1.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
<<<<<<< HEAD
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
=======
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
>>>>>>> 9d8b7eebba08273004465e73c702a9d3b1a4effa
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.apolis.groceryapplication1.R
import com.apolis.groceryapplication1.adapters.AdapterAddress
<<<<<<< HEAD
import com.apolis.groceryapplication1.app.Endpoints
import com.apolis.groceryapplication1.helpers.SessionManager
import com.apolis.groceryapplication1.helpers.toast
import com.apolis.groceryapplication1.models.Address
import com.apolis.groceryapplication1.models.AddressResponse
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_address.*
import kotlinx.android.synthetic.main.app_bar.*

class AddressActivity : AppCompatActivity()/*, AdapterAddress.OnAdapterInteraction*/{

    lateinit var addressList: ArrayList<Address>
    lateinit var adapterAddress: AdapterAddress
    lateinit var address: Address
    lateinit var sessionManager: SessionManager
=======
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
>>>>>>> 9d8b7eebba08273004465e73c702a9d3b1a4effa

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address)

<<<<<<< HEAD
        image_view_empty_address.visibility = VISIBLE
        text_view_empty_address.visibility = VISIBLE

        //Get address data
        getAddress()

=======
>>>>>>> 9d8b7eebba08273004465e73c702a9d3b1a4effa
        init()
    }

    private fun init() {
<<<<<<< HEAD

=======
>>>>>>> 9d8b7eebba08273004465e73c702a9d3b1a4effa
        //Set up customized toolbar
        var toolbar = tool_bar
        toolbar.title = "Manage Address"
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

<<<<<<< HEAD
        //Add data to linear recycler view
        addressList = ArrayList<Address>()
        adapterAddress = AdapterAddress(this, addressList)
        recycler_view_address_linear.layoutManager = LinearLayoutManager(this)
        recycler_view_address_linear.adapter = adapterAddress

        if(addressList.isNullOrEmpty()){
            image_view_empty_address.visibility = GONE
            text_view_empty_address.visibility = GONE
        }

=======
        //Get address data
        getAddress()

        //Add data to linear recycler view
        adapterAddress = AdapterAddress(this,addressList)
        recycler_view_address_linear.layoutManager = LinearLayoutManager(this)
        recycler_view_address_linear.adapter = adapterAddress

>>>>>>> 9d8b7eebba08273004465e73c702a9d3b1a4effa
        button_add_new_address.setOnClickListener {
            //navigate to AddAddress Activity
            startActivity(Intent(this, AddAddressActivity::class.java))
        }
<<<<<<< HEAD
=======

>>>>>>> 9d8b7eebba08273004465e73c702a9d3b1a4effa
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

<<<<<<< HEAD
//    override fun onClickedItemListener(itemView: View, address: Address, position: Int) {
//        itemView.text_view_delete_address.setOnClickListener{
//            this.toast("Delete clicked")
//            deleteAddress(address,position)
//            if(addressList.isEmpty()){
//                image_view_empty_bag.visibility = VISIBLE
//                text_view_empty_address.visibility = VISIBLE
//            }
//        }
//    }

    //Get data from API
    private fun getAddress() {
        address = Address()
        sessionManager = SessionManager(this)
        val userId = sessionManager.getUserId()
        val url = Endpoints.getAddressURL()+"/"+userId
=======
    //Get data from API
    private fun getAddress() {
        address = Address()
        val url = Endpoints.getAddressURL()+"/5f114569487f410017816626"
        //val url = Endpoints.getAddressURL()+"/"+address.userId
>>>>>>> 9d8b7eebba08273004465e73c702a9d3b1a4effa
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

<<<<<<< HEAD
//    //Delete address data from API
//    fun deleteAddress(address: Address, position: Int) {
//        val url = Endpoints.getAddressURL()+ "/" + address._id
//        var requestQueue = Volley.newRequestQueue(this)
//        var request = StringRequest(
//                Request.Method.DELETE, url,
//                Response.Listener {
//                    addressList.removeAt(position)
//                    adapterAddress.notifyItemRemoved(position)
//                    adapterAddress.notifyItemChanged(position, addressList.size)
//                    this.toast("successfully deleted the address from server")
//                },
//                Response.ErrorListener {
//                    this.toast("Error in deleting address")
//                    Log.d("ABC",it.message.toString())
//                }
//        )
//        requestQueue.add(request)
//    }

//    //Edit address data from API
//    fun editAddress() {
//        var myIntent = Intent(this, EditAddressActivity::class.java)
//        myIntent.putExtra(Address.ADDRESS_KEY, address)
//        startActivity(myIntent)
//    }

=======
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
>>>>>>> 9d8b7eebba08273004465e73c702a9d3b1a4effa
}