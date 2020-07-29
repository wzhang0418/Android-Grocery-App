package com.apolis.groceryapplication1.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
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
import com.apolis.groceryapplication1.helpers.SessionManager
import com.apolis.groceryapplication1.models.Address
import kotlinx.android.synthetic.main.activity_add_address.*
import kotlinx.android.synthetic.main.activity_edit_address.*
import kotlinx.android.synthetic.main.app_bar.*
import org.json.JSONObject

class EditAddressActivity : AppCompatActivity() {
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_address)

        init()
    }

    private fun init() {
        sharedPreferences = getSharedPreferences(SessionManager.FILE_NAME, Context.MODE_PRIVATE)

        //Set up customized toolbar
        var toolbar = tool_bar
        toolbar.title = "Edit Address"
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //Keep the name and mobile stored in sharedpref
        var savedName = sharedPreferences.getString(SessionManager.KEY_NAME,null)
        var savedMobile = sharedPreferences.getString(SessionManager.KEY_MOBILE,null)
        edit_text_name_receiver_edit.setText(savedName)
        edit_text_mobile_receiver_edit.setText(savedMobile)


        var intent = intent
        var address = intent.getSerializableExtra(Address.ADDRESS_KEY) as Address
        edit_text_city_edit.setText(address.city)
        edit_text_house_number_edit.setText(address.houseNo)
        edit_text_pincode_edit.setText(address.pincode.toString())
        edit_text_street_name_edit.setText(address.streetName)
        //edit_text_type.setText(address.type)

        button_save_address_edit.setOnClickListener {
            address.city = edit_text_city_edit.text.toString()
            address.houseNo = edit_text_house_number_edit.text.toString()
            address.streetName = edit_text_street_name_edit.text.toString()
            //address.type = edit_text_type.text.toString()
            address.pincode = edit_text_pincode_edit.text.toString().toInt()
            updateAddress(address)
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

    private fun updateAddress(address: Address) {
        var params = HashMap<String, Any?>()
        params["city"] = address.city
        params["houseNo"] = address.houseNo
        params["pincode"] = address.pincode
        params["streetName"] = address.streetName
        params["type"] = address.type
        params["userId"] = address.userId
        val jsonObject = JSONObject(params as Map<*, *>)
        val url = Endpoints.getAddressURL() + "/" +address._id
        var request = JsonObjectRequest(
                Request.Method.PUT, url,
                jsonObject,
                Response.Listener {
                    startActivity(Intent(this, AddressActivity::class.java))
                },
                Response.ErrorListener {
                    Toast.makeText(applicationContext, it.toString(), Toast.LENGTH_SHORT).show()
                })
        Volley.newRequestQueue(this).add(request)
    }
}