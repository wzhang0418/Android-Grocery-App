package com.apolis.groceryapplication1.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apolis.groceryapplication1.R
import com.apolis.groceryapplication1.activities.ProductDetailActivity
import com.apolis.groceryapplication1.app.Endpoints
import com.apolis.groceryapplication1.models.Product
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_product_adapter.view.*

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

        fun bind(product: Product) {
            itemView.text_view_product_name.text = product.productName
            itemView.text_view_product_price.text = "$" + product.price.toString()

            Picasso.get()
                .load(Endpoints.getImage() + product.image)
                .placeholder(R.drawable.place_holder)
                .error(R.drawable.no_image)
                .into(itemView.image_view_product)

            itemView.setOnClickListener {
                val intent = Intent(mContext, ProductDetailActivity::class.java)
                intent.putExtra(Product.PRODUCT_KEY, product)
                mContext.startActivity(intent)
            }


        }

    }

}