package com.apolis.groceryapplication1.adapters

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
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
import kotlinx.android.synthetic.main.row_address_adapter.view.*

class AdapterAddress(var mContext: Context, var mList: ArrayList<Address>): RecyclerView.Adapter<AdapterAddress.MyViewHolder>() {

//    private var listener: OnAdapterInteraction? = null

    var addressList: ArrayList<Address> = ArrayList()
    lateinit var sessionManager: SessionManager
    lateinit var addressActivity: AddressActivity
    lateinit var sharedPreferences: SharedPreferences

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

            itemView.setOnClickListener {
                // Save and use this clicked address info
                mContext.startActivity(Intent(mContext, PaymentActivity::class.java))
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