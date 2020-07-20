package com.apolis.groceryapplication1.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apolis.groceryapplication1.R
import com.apolis.groceryapplication1.app.Endpoints
import com.apolis.groceryapplication1.database.DBHelper
import com.apolis.groceryapplication1.models.Product
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_cart_detail_adapter.view.*

class AdapterCart(var mContext: Context, var mList: ArrayList<Product>): RecyclerView.Adapter<AdapterCart.MyViewHolder>() {

    lateinit var dbHelper: DBHelper

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view = LayoutInflater.from(mContext).inflate(R.layout.row_cart_detail_adapter, parent, false)
        var viewHolder = MyViewHolder(view)
        return viewHolder
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var product = mList[position]
        holder.bind(product, position)
    }

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(product: Product, position: Int){

            dbHelper = DBHelper(mContext)

            itemView.text_view_add_quantity_cart.text = "${product.qty}"

            var quantityProduct = itemView.text_view_add_quantity_cart.text.toString().toInt()

            itemView.text_view_name_cart.text = "${product.productName}"
            itemView.text_view_price_cart.text = "$" + "${(quantityProduct* product.price!!)}"
            itemView.text_view_market_price_cart.text = "$" + "${quantityProduct* product.mrp!!}"
            itemView.text_view_save_price_cart.text = "you save $" + "${(quantityProduct*(product.mrp-product.price))}"

            Picasso.get()
                    .load(Endpoints.getImage() + product.image)
                    .placeholder(R.drawable.place_holder)
                    .error(R.drawable.no_image)
                    .into(itemView.card_view_cart_detail.image_view_cart_detail)

            itemView.button_add_cart.setOnClickListener{
                product.qty = product.qty+1
                dbHelper.addToCart(product)
                Log.d("RESULT", product.qty.toString())

                itemView.text_view_add_quantity_cart.text = product.qty.toString()

            }
            itemView.button_minus_cart.setOnClickListener {
                if(quantityProduct>=2)
                    itemView.text_view_add_quantity_cart.text = (--quantityProduct).toString()
                else{
                    dbHelper.deleteCart(product._id!!)
                    notifyItemRemoved(position)
                }
            }

            itemView.image_button_delete_cart.setOnClickListener{
                dbHelper.deleteCart(product._id!!)
                notifyItemRemoved(position)
            }

        }
    }
}