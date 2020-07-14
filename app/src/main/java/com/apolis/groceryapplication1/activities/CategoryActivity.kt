package com.apolis.groceryapplication1.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.apolis.groceryapplication1.R
import com.apolis.groceryapplication1.adapters.AdapterCategory
import com.apolis.groceryapplication1.app.Endpoints
import com.apolis.groceryapplication1.models.Category
import com.apolis.groceryapplication1.models.CategoryResponse
import com.apolis.groceryapplication1.models.Product
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_category.*

class CategoryActivity : AppCompatActivity() {

    var categoryList: ArrayList<Category> = ArrayList()
    lateinit var adapterCategory: AdapterCategory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        init()
    }

    private fun init() {

        getData()

        adapterCategory = AdapterCategory(this,categoryList)
        recycler_view_grid.layoutManager = GridLayoutManager(this,2)
        recycler_view_grid.adapter = adapterCategory
    }

    private fun getData() {
        var requestQueue = Volley.newRequestQueue(this)
        var request = StringRequest(
            Request.Method.GET,
            Endpoints.getCategory(),
            Response.Listener {
                progress_bar.visibility = View.GONE
                var gson = GsonBuilder().create()
                var categoryResponse = gson.fromJson(it, CategoryResponse::class.java)
                adapterCategory.setData(categoryResponse.categoryData)
            },
            Response.ErrorListener {
                Log.d("ABC",it.message.toString())
            }
        )
        requestQueue.add(request)
    }
}