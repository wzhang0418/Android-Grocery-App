package com.apolis.groceryapplication1.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apolis.groceryapplication1.R
import com.apolis.groceryapplication1.activities.SubCategoryActivity
import com.apolis.groceryapplication1.app.Endpoints
import com.apolis.groceryapplication1.models.Category
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_category_adapter.view.*

class AdapterCategory(var mContext: Context, var mList: ArrayList<Category>) : RecyclerView.Adapter<AdapterCategory.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(mContext).inflate(R.layout.row_category_adapter, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var categoryData = mList[position]
        holder.bind(categoryData)
    }

    fun setData(list: List<Category>){
        mList = list as ArrayList<Category>
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(categoryData: Category) {

            itemView.text_view_category.text = categoryData.catName

            Picasso.get()
                .load(Endpoints.getImage() + categoryData.catImage)
                .placeholder(R.drawable.place_holder)
                .error(R.drawable.no_image)
                .into(itemView.image_view_category)

            itemView.setOnClickListener {
                val intent = Intent(mContext, SubCategoryActivity::class.java)
                intent.putExtra(Category.CATEGORY_KEY, categoryData.catId)
                mContext.startActivity(intent)
            }

        }

    }

}