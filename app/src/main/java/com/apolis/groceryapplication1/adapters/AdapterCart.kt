package com.apolis.groceryapplication1.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
<<<<<<< HEAD
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apolis.groceryapplication1.R
import com.apolis.groceryapplication1.activities.CartDetailActivity
import com.apolis.groceryapplication1.app.Endpoints
import com.apolis.groceryapplication1.database.DBHelper
import com.apolis.groceryapplication1.helpers.toast
import com.apolis.groceryapplication1.models.Product
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_cart_detail.*
import kotlinx.android.synthetic.main.row_cart_detail_adapter.view.*
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat

class AdapterCart(var mContext: Context, var mList: MutableList<Product>): RecyclerView.Adapter<AdapterCart.MyViewHolder>() {
=======
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apolis.groceryapplication1.R
import com.apolis.groceryapplication1.app.Endpoints
import com.apolis.groceryapplication1.database.DBHelper
import com.apolis.groceryapplication1.models.Product
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_cart_detail_adapter.view.*

class AdapterCart(var mContext: Context, var mList: ArrayList<Product>): RecyclerView.Adapter<AdapterCart.MyViewHolder>() {
>>>>>>> 9d8b7eebba08273004465e73c702a9d3b1a4effa

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
<<<<<<< HEAD

=======
>>>>>>> 9d8b7eebba08273004465e73c702a9d3b1a4effa
        fun bind(product: Product, position: Int){

            dbHelper = DBHelper(mContext)

            itemView.text_view_add_quantity_cart.text = "${product.qty}"

            var quantityProduct = itemView.text_view_add_quantity_cart.text.toString().toInt()
<<<<<<< HEAD
            mContext.toast("quantityProduct is " + quantityProduct)
=======
>>>>>>> 9d8b7eebba08273004465e73c702a9d3b1a4effa

            itemView.text_view_name_cart.text = "${product.productName}"
            itemView.text_view_price_cart.text = "$" + "${(quantityProduct* product.price!!)}"
            itemView.text_view_market_price_cart.text = "$" + "${quantityProduct* product.mrp!!}"
<<<<<<< HEAD
            itemView.text_view_save_price_cart.text = "you save $" + "${BigDecimal(quantityProduct*(product.mrp!! -product.price)).setScale(2, RoundingMode.HALF_EVEN)}"
=======
            itemView.text_view_save_price_cart.text = "you save $" + "${(quantityProduct*(product.mrp-product.price))}"
>>>>>>> 9d8b7eebba08273004465e73c702a9d3b1a4effa

            Picasso.get()
                    .load(Endpoints.getImage() + product.image)
                    .placeholder(R.drawable.place_holder)
                    .error(R.drawable.no_image)
                    .into(itemView.card_view_cart_detail.image_view_cart_detail)

            itemView.button_add_cart.setOnClickListener{
                product.qty = product.qty+1
<<<<<<< HEAD
                dbHelper.updateQuantity(product, product.qty)
                //Log.d("RESULT", product.qty.toString())

                itemView.text_view_add_quantity_cart.text = product.qty.toString()
            }
            itemView.button_minus_cart.setOnClickListener {
                mContext.toast("Quantity is "+"${product.qty}")
                if(product.qty>=2) {
                    product.qty = product.qty-1
                    dbHelper.updateQuantity(product, product.qty)
                    itemView.text_view_add_quantity_cart.text = product.qty.toString()
                }
                else{
                    dbHelper.deleteProduct(product._id!!)
                    notifyItemRemoved(position)
                    mList.removeAt(position)
                    notifyDataSetChanged()
                }

            }

            itemView.image_button_delete_cart.setOnClickListener{
                dbHelper.deleteProduct(product._id!!)
                mList.removeAt(position)
                notifyDataSetChanged()

                if(mList.isNullOrEmpty()){
                    (mContext as CartDetailActivity).image_view_empty_cart.visibility = VISIBLE
                    (mContext as CartDetailActivity).text_view_empty_cart.visibility = VISIBLE
                    (mContext as CartDetailActivity).relative_layout_total.visibility = GONE
                    (mContext as CartDetailActivity).button_checkout.visibility = GONE
                    (mContext as CartDetailActivity).button_go_shopping.visibility = VISIBLE
                }
=======
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
>>>>>>> 9d8b7eebba08273004465e73c702a9d3b1a4effa
            }

        }
    }
}