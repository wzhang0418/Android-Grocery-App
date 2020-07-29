package com.apolis.groceryapplication1.adapters

import android.content.Context
import android.content.Intent
<<<<<<< HEAD
import android.content.SharedPreferences
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
=======
import android.util.Log
import android.view.LayoutInflater
import android.view.View
>>>>>>> 9d8b7eebba08273004465e73c702a9d3b1a4effa
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
<<<<<<< HEAD
import com.android.volley.toolbox.Volley.newRequestQueue
import com.apolis.groceryapplication1.R
import com.apolis.groceryapplication1.activities.AddressActivity
import com.apolis.groceryapplication1.activities.EditAddressActivity
import com.apolis.groceryapplication1.activities.PaymentActivity
import com.apolis.groceryapplication1.app.Endpoints
import com.apolis.groceryapplication1.helpers.SessionManager
import com.apolis.groceryapplication1.helpers.SessionManager.Companion.KEY_RECEIVER_MOBILE
import com.apolis.groceryapplication1.helpers.SessionManager.Companion.KEY_RECEIVER_NAME
import com.apolis.groceryapplication1.helpers.toast
import com.apolis.groceryapplication1.models.Address
import kotlinx.android.synthetic.main.activity_address.*
=======
import com.android.volley.toolbox.Volley
import com.apolis.groceryapplication1.R
import com.apolis.groceryapplication1.activities.AddressActivity
import com.apolis.groceryapplication1.activities.PaymentActivity
import com.apolis.groceryapplication1.app.Endpoints
import com.apolis.groceryapplication1.database.DBHelper
import com.apolis.groceryapplication1.helpers.SessionManager
import com.apolis.groceryapplication1.helpers.toast
import com.apolis.groceryapplication1.models.Address
import com.apolis.groceryapplication1.models.User
>>>>>>> 9d8b7eebba08273004465e73c702a9d3b1a4effa
import kotlinx.android.synthetic.main.row_address_adapter.view.*

class AdapterAddress(var mContext: Context, var mList: ArrayList<Address>): RecyclerView.Adapter<AdapterAddress.MyViewHolder>() {

<<<<<<< HEAD
//    private var listener: OnAdapterInteraction? = null

    var addressList: ArrayList<Address> = ArrayList()
    lateinit var sessionManager: SessionManager
    lateinit var addressActivity: AddressActivity
    lateinit var sharedPreferences: SharedPreferences
=======
    lateinit var sessionManager: SessionManager
    lateinit var addressActivity: AddressActivity
>>>>>>> 9d8b7eebba08273004465e73c702a9d3b1a4effa

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view = LayoutInflater.from(mContext).inflate(R.layout.row_address_adapter, parent, false)
        var viewHolder = MyViewHolder(view)
        return viewHolder
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var address = mList[position]
        holder.bind(address, position)
    }

    fun setData(list: ArrayList<Address>){
        mList = list
        notifyDataSetChanged()
    }

<<<<<<< HEAD
//    interface OnAdapterInteraction {
//        fun onClickedItemListener(itemView: View, address: Address, position: Int)
//    }
//
//    fun setAdapterListener (onAdapterInteraction: OnAdapterInteraction) {
//        listener = onAdapterInteraction
//    }

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(address: Address, position: Int){

            sharedPreferences = mContext.getSharedPreferences(SessionManager.FILE_NAME, Context.MODE_PRIVATE)
            sessionManager = SessionManager(mContext)

            //Keep the receiver's name and mobile stored in the sharedpref
            itemView.text_view_name_address.text = sharedPreferences.getString(KEY_RECEIVER_NAME, null)
            itemView.text_view_mobile_number_address.text = sharedPreferences.getString(KEY_RECEIVER_MOBILE, null)

            itemView.text_view_address_type.text = "${address.type}"
            itemView.text_view_address_address.text = "${address.streetName}, ${address.houseNo}"
            itemView.text_view_zipcode_address.text = "${address.pincode}"

//            listener?.onClickedItemListener(itemView, address, position)
=======
    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(address: Address, position: Int){

            addressActivity = AddressActivity()
            sessionManager = SessionManager(mContext)

            itemView.text_view_address_type.text = "${address.type}"
            itemView.text_view_name_address.text = "Wenzhao Zhang"
            itemView.text_view_address_address.text = "${address.streetName}, ${address.houseNo}"
            itemView.text_view_zipcode_address.text = "${address.pincode}"
            itemView.text_view_mobile_number_address.text = "13210534488"

            //hardcode values
//            //itemView.text_view_address_type.text = "address type"
//            itemView.text_view_name_address.text = "name"
//            itemView.text_view_address_address.text = "address"
//            itemView.text_view_zipcode_address.text = "zipcode"
//            itemView.text_view_mobile_number_address.text = 12222222.toString()
>>>>>>> 9d8b7eebba08273004465e73c702a9d3b1a4effa

            itemView.setOnClickListener {
                // Save and use this clicked address info
                mContext.startActivity(Intent(mContext, PaymentActivity::class.java))
<<<<<<< HEAD
                mContext.toast("Item in Adapter is clicked")
            }

            itemView.text_view_edit_address.setOnClickListener {
                mContext.toast("Edit in Adapter is clicked")
                var myIntent = Intent(mContext, EditAddressActivity::class.java)
                myIntent.putExtra(Address.ADDRESS_KEY, address)
                mContext.startActivity(myIntent)
            }
            itemView.text_view_delete_address.setOnClickListener{
                deleteAddress(address,position)
            }
        }

        //Delete address data from API
        fun deleteAddress(address: Address, position: Int) {
            val url = Endpoints.getAddressURL()+ "/" + address._id
            var requestQueue = newRequestQueue(mContext)
            var request = StringRequest(
                    Request.Method.DELETE, url,
                    Response.Listener {
                        mList.removeAt(position)
                        notifyItemRemoved(position)
                        notifyItemChanged(position, mList.size)
                        mContext.toast("successfully deleted the address from server")

                        if(mList.isNullOrEmpty()){
                            (mContext as AddressActivity).image_view_empty_address.visibility = VISIBLE
                            (mContext as AddressActivity).text_view_empty_address.visibility = VISIBLE
                        }
=======
            }

            itemView.text_view_edit_address.setOnClickListener {
                //
            }
            itemView.text_view_delete_address.setOnClickListener{
                deleteAddress()
                notifyItemRemoved(position)
            }

        }

        //Delete address data from API
        fun deleteAddress() {
            val url = Endpoints.getAddressURL()+"/5f189b62c2201a001746fc26"
            var requestQueue = Volley.newRequestQueue(mContext)
            var request = StringRequest(
                    Request.Method.DELETE, url,
                    Response.Listener {
//                    var gson = GsonBuilder().create()
//                    var addressResponse = gson.fromJson(it, AddressResponse::class.java)
//                    adapterAddress.setData(addressResponse.address)
                        mContext.toast("successfully deleted the address")
>>>>>>> 9d8b7eebba08273004465e73c702a9d3b1a4effa
                    },
                    Response.ErrorListener {
                        mContext.toast("Error in deleting address")
                        Log.d("ABC",it.message.toString())
                    }
            )
            requestQueue.add(request)
        }

    }
}