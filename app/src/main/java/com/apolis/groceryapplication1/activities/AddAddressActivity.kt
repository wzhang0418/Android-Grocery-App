package com.apolis.groceryapplication1.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.apolis.groceryapplication1.R
import com.apolis.groceryapplication1.adapters.AdapterAddress
import com.apolis.groceryapplication1.app.Endpoints
import com.apolis.groceryapplication1.helpers.SessionManager
import com.apolis.groceryapplication1.helpers.toast
import com.apolis.groceryapplication1.models.*
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_add_address.*
import kotlinx.android.synthetic.main.activity_category.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.app_bar.*
import org.json.JSONObject

class AddAddressActivity : AppCompatActivity() {

    var addressList: ArrayList<Address> = ArrayList()
    lateinit var adapterAddress: AdapterAddress
    lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_address)

        init()
    }

    private fun init() {
        //Set up customized toolbar
        var toolbar = tool_bar
        toolbar.title = "Add Address"
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //click "save address" button
        button_save_address.setOnClickListener {
            postAddress()
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

    private fun postAddress(){
        sessionManager = SessionManager(this)
        this.toast("userId = " + sessionManager.getUserId().toString())

        var houseNo = edit_text_house_number.text.toString()
        var city = edit_text_city.text.toString()
        var street = edit_text_street_name.text.toString()
        var pincode = edit_text_pincode.text.toString()
        //var userId = sessionManager.getUserId()

        var addressInfo = HashMap<String, Any?>()
        addressInfo["houseNo"] = houseNo
        addressInfo["pincode"] = pincode.toInt()
        addressInfo["userId"] = "5f114569487f410017816626"
        //addressInfo["userId"] = userId
        addressInfo["streetName"] = street
        addressInfo["city"] = city
        addressInfo["type"] = "Home"

        var jsonObject = JSONObject(addressInfo as Map<*,*>)
        var request = JsonObjectRequest(Request.Method.POST, Endpoints.getAddressURL(),jsonObject,
                Response.Listener {
                    this.toast("Successfully post address")
                    startActivity(Intent(this,AddressActivity::class.java))
                },
                Response.ErrorListener{
                    this.toast("Error posting address")
                    //Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                })
        Volley.newRequestQueue(this).add(request)
    }

}