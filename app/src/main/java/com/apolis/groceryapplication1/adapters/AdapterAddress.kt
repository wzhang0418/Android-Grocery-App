package com.apolis.groceryapplication1.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
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
import kotlinx.android.synthetic.main.row_address_adapter.view.*

class AdapterAddress(var mContext: Context, var mList: ArrayList<Address>): RecyclerView.Adapter<AdapterAddress.MyViewHolder>() {

    lateinit var sessionManager: SessionManager
    lateinit var addressActivity: AddressActivity

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

            itemView.setOnClickListener {
                // Save and use this clicked address info
                mContext.startActivity(Intent(mContext, PaymentActivity::class.java))
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