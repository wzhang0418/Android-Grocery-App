package com.apolis.groceryapplication1.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apolis.groceryapplication1.R
import com.apolis.groceryapplication1.activities.PaymentActivity
import com.apolis.groceryapplication1.database.DBHelper
import com.apolis.groceryapplication1.models.Address
import kotlinx.android.synthetic.main.row_address_adapter.view.*

class AdapterAddress(var mContext: Context, var mList: ArrayList<Address>): RecyclerView.Adapter<AdapterAddress.MyViewHolder>() {

    lateinit var dbHelper: DBHelper

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

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(address: Address, position: Int){

            dbHelper = DBHelper(mContext)

            itemView.text_view_address_type.text = "${address.type}"
            //TODO: Add correct user name
            itemView.text_view_name_address.text = "${address.userId}"
            itemView.text_view_address_address.text = "${address.streetName} + {, } + ${address.houseNo}"
            itemView.text_view_zipcode_address.text = "${address.pincode}"
            //TODO: Add correct user mobile number
            itemView.text_view_mobile_number_address.text = "${address.userId}"

            itemView.setOnClickListener {
                //TODO: Save and use this clicked address info
                mContext.startActivity(Intent(mContext, PaymentActivity::class.java))
            }

            itemView.text_view_edit_address.setOnClickListener {
                //TODO:
            }
            itemView.text_view_delete_address.setOnClickListener{
                //TODO:
                //notifyItemRemoved(position)
            }

        }
    }
}