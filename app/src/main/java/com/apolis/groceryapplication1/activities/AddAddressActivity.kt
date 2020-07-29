package com.apolis.groceryapplication1.activities

<<<<<<< HEAD
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.View.VISIBLE
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
=======
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
>>>>>>> 9d8b7eebba08273004465e73c702a9d3b1a4effa
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
<<<<<<< HEAD
=======
import com.android.volley.toolbox.StringRequest
>>>>>>> 9d8b7eebba08273004465e73c702a9d3b1a4effa
import com.android.volley.toolbox.Volley
import com.apolis.groceryapplication1.R
import com.apolis.groceryapplication1.adapters.AdapterAddress
import com.apolis.groceryapplication1.app.Endpoints
import com.apolis.groceryapplication1.helpers.SessionManager
<<<<<<< HEAD
import com.apolis.groceryapplication1.helpers.SessionManager.Companion.FILE_NAME
import com.apolis.groceryapplication1.helpers.toast
import com.apolis.groceryapplication1.models.Address
import kotlinx.android.synthetic.main.activity_add_address.*
import kotlinx.android.synthetic.main.activity_address.*
=======
import com.apolis.groceryapplication1.helpers.toast
import com.apolis.groceryapplication1.models.*
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_add_address.*
import kotlinx.android.synthetic.main.activity_category.*
>>>>>>> 9d8b7eebba08273004465e73c702a9d3b1a4effa
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.app_bar.*
import org.json.JSONObject

class AddAddressActivity : AppCompatActivity() {

    var addressList: ArrayList<Address> = ArrayList()
    lateinit var adapterAddress: AdapterAddress
    lateinit var sessionManager: SessionManager

<<<<<<< HEAD
    lateinit var view: View

    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor

    var radioGroup: RadioGroup? = null
    lateinit var radioButton: RadioButton

=======
>>>>>>> 9d8b7eebba08273004465e73c702a9d3b1a4effa
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_address)

<<<<<<< HEAD
        sharedPreferences = getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
        sessionManager = SessionManager(this)

=======
>>>>>>> 9d8b7eebba08273004465e73c702a9d3b1a4effa
        init()
    }

    private fun init() {
        //Set up customized toolbar
        var toolbar = tool_bar
        toolbar.title = "Add Address"
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

<<<<<<< HEAD
        //Keep the name and mobile stored in sharedPreference
        var savedName = sharedPreferences.getString(SessionManager.KEY_NAME,null)
        var savedMobile = sharedPreferences.getString(SessionManager.KEY_MOBILE,null)
        edit_text_name_receiver.setText(savedName)
        edit_text_mobile_receiver.setText(savedMobile)

=======
>>>>>>> 9d8b7eebba08273004465e73c702a9d3b1a4effa
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

<<<<<<< HEAD
        //Store radio button selection
        view = View(this)
        radioGroup = findViewById(R.id.radio_group)
        val selectType: Int = radioGroup!!.checkedRadioButtonId
        radioButton = findViewById(selectType)

        var receiverName = edit_text_name_receiver.text.toString()
        var receiverMobile = edit_text_mobile_receiver.text.toString()

=======
>>>>>>> 9d8b7eebba08273004465e73c702a9d3b1a4effa
        var houseNo = edit_text_house_number.text.toString()
        var city = edit_text_city.text.toString()
        var street = edit_text_street_name.text.toString()
        var pincode = edit_text_pincode.text.toString()
<<<<<<< HEAD
        var userId = sessionManager.getUserId()
        var type = radioButton.text.toString()

=======
        //var userId = sessionManager.getUserId()
>>>>>>> 9d8b7eebba08273004465e73c702a9d3b1a4effa

        var addressInfo = HashMap<String, Any?>()
        addressInfo["houseNo"] = houseNo
        addressInfo["pincode"] = pincode.toInt()
<<<<<<< HEAD
        addressInfo["userId"] = userId
        addressInfo["streetName"] = street
        addressInfo["city"] = city
        addressInfo["type"] = type
=======
        addressInfo["userId"] = "5f114569487f410017816626"
        //addressInfo["userId"] = userId
        addressInfo["streetName"] = street
        addressInfo["city"] = city
        addressInfo["type"] = "Home"
>>>>>>> 9d8b7eebba08273004465e73c702a9d3b1a4effa

        var jsonObject = JSONObject(addressInfo as Map<*,*>)
        var request = JsonObjectRequest(Request.Method.POST, Endpoints.getAddressURL(),jsonObject,
                Response.Listener {
<<<<<<< HEAD
                    //Every time save address, the name and mobile are saved in sharedpref
                    sessionManager.saveReceiver(receiverName, receiverMobile)
                    startActivity(Intent(this,AddressActivity::class.java))
                    this.toast("Successfully post address in server")
                },
                Response.ErrorListener{
                    this.toast("Error posting address in server")
=======
                    this.toast("Successfully post address")
                    startActivity(Intent(this,AddressActivity::class.java))
                },
                Response.ErrorListener{
                    this.toast("Error posting address")
>>>>>>> 9d8b7eebba08273004465e73c702a9d3b1a4effa
                    //Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                })
        Volley.newRequestQueue(this).add(request)
    }

}