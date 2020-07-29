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
import com.apolis.groceryapplication1.activities.CartDetailActivity
import com.apolis.groceryapplication1.activities.ProductDetailActivity
import com.apolis.groceryapplication1.app.Endpoints
import com.apolis.groceryapplication1.database.DBHelper
import com.apolis.groceryapplication1.helpers.toast
import com.apolis.groceryapplication1.models.Product
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_product_detail.view.*
import kotlinx.android.synthetic.main.row_cart_detail_adapter.view.*
import kotlinx.android.synthetic.main.row_product_adapter.view.*
import kotlinx.android.synthetic.main.row_product_adapter.view.button_add
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.properties.Delegates

class AdapterProduct(var mContext: Context, var mList: ArrayList<Product>) : RecyclerView.Adapter<AdapterProduct.ProductViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        var view = LayoutInflater.from(mContext).inflate(R.layout.row_product_adapter, parent, false)
        return ProductViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        var product = mList[position]
        holder.bind(product)
    }

    fun setData(list: ArrayList<Product>){
        mList = list
        notifyDataSetChanged()
    }

    inner class ProductViewHolder(private var view: View) : RecyclerView.ViewHolder(view) {

        lateinit var dbHelper: DBHelper
        var quantityProduct: Int? = null

        fun bind(product: Product) {
            quantityProduct = product.qty

            itemView.text_view_add_quantity.text = "1"

            //mContext.toast("quantityProduct is "+"$quantityProduct")
            itemView.text_view_name.text = "${product.productName}"
            itemView.text_view_price.text = "$" + "${product.price!!}"
            itemView.text_view_market_price.text = "$" + "${product.mrp!!}"
            itemView.text_view_save_price.text = "you save $" + "${BigDecimal((product.mrp!! -product.price)).setScale(2, RoundingMode.HALF_EVEN)}"

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

                product.qty = 1
                dbHelper.addToCart(product)
            }

            itemView.button_add.setOnClickListener{
                product.qty = product.qty+1
                dbHelper.updateQuantity(product, product.qty)
                //Log.d("RESULT", product.qty.toString())
                itemView.text_view_add_quantity.text = product.qty.toString()
            }
            itemView.button_minus.setOnClickListener {
                if(product.qty>=2){
                    product.qty = product.qty-1
                    dbHelper.updateQuantity(product, product.qty)
                    itemView.text_view_add_quantity.text = product.qty.toString()
                }
                else{
                    dbHelper.deleteProduct(product._id!!)
                    itemView.button_add_minus.visibility = GONE
                    itemView.button_ADD.visibility = VISIBLE
                }
            }
        }
    }
}