package com.apolis.groceryapplication1.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apolis.groceryapplication1.R
import com.apolis.groceryapplication1.activities.ProductDetailActivity
import com.apolis.groceryapplication1.app.Endpoints
import com.apolis.groceryapplication1.database.DBHelper
import com.apolis.groceryapplication1.models.Product
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_product_detail.view.*
import kotlinx.android.synthetic.main.row_cart_detail_adapter.view.*
import kotlinx.android.synthetic.main.row_product_adapter.view.*
import kotlinx.android.synthetic.main.row_product_adapter.view.button_add
import kotlin.properties.Delegates

class AdapterProduct(var mContext: Context, var mList: ArrayList<Product>) : RecyclerView.Adapter<AdapterProduct.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(mContext).inflate(R.layout.row_product_adapter, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var product = mList[position]
        holder.bind(product)
    }

    fun setData(list: ArrayList<Product>){
        mList = list
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        lateinit var dbHelper: DBHelper

        fun bind(product: Product) {

            var quantityProduct = itemView.text_view_add_quantity.text.toString().toInt()
            itemView.text_view_add_quantity.text = "1"

            itemView.text_view_name.text = "${product.productName}"
            itemView.text_view_price.text = "$" + "${(quantityProduct* product.price!!)}"
            itemView.text_view_market_price.text = "$" + "${quantityProduct* product.mrp!!}"
            itemView.text_view_save_price.text = "you save $" + "${(quantityProduct*(product.mrp!! -product.price))}"

            Picasso.get()
                .load(Endpoints.getImage() + product.image)
                .placeholder(R.drawable.place_holder)
                .error(R.drawable.no_image)
                .into(itemView.card_view_product.image_view_row)

            dbHelper = DBHelper(mContext)

            itemView.setOnClickListener {
                val intent = Intent(mContext, ProductDetailActivity::class.java)
                intent.putExtra(Product.PRODUCT_KEY, product)
                mContext.startActivity(intent)
            }

            itemView.button_add_minus.visibility = GONE
            itemView.button_ADD.setOnClickListener {
                itemView.button_ADD.visibility = GONE
                itemView.button_add_minus.visibility = VISIBLE
            }

            itemView.button_add.setOnClickListener{
                product.qty = product.qty+1
                dbHelper.addToCart(product)
                Log.d("RESULT", product.qty.toString())
                itemView.text_view_add_quantity.text = (++quantityProduct).toString()
            }
            itemView.button_minus.setOnClickListener {
                if(quantityProduct>=2){
                    dbHelper.updateProduct(product)
                    itemView.text_view_add_quantity.text = (--quantityProduct).toString()
                }
                else{
                    dbHelper.deleteCart(product._id!!)
                    itemView.button_add_minus.visibility = GONE
                    itemView.button_ADD.visibility = VISIBLE
                }
            }
        }

    }

}