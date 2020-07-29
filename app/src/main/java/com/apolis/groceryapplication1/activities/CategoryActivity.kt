package com.apolis.groceryapplication1.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
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
import com.custom.sliderimage.logic.SliderImage
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_category.*
import kotlinx.android.synthetic.main.app_bar.*

class CategoryActivity : AppCompatActivity() {

    var categoryList: ArrayList<Category> = ArrayList()
    lateinit var adapterCategory: AdapterCategory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        init()
    }

    private fun init() {

        // Create slider image component
        val sliderImage = SliderImage(this)

        // add images URLs
        val images = listOf(
                "https://www.rickbayless.com/wp-content/uploads/2020/01/Restaurant-Week-Slider-2020-01-1-768x391.jpg",
                "https://www.tossed.com/wp-content/uploads/2017/11/tossed-your-favorities-slider-1350x553.jpg",
                "https://www.dailynews.com/wp-content/uploads/2020/06/LDN-L-DINE-INDIANFOOD-0605-1-1.jpg?w=620",
                "https://www.tossed.com/wp-content/uploads/2017/11/tossed-design-uor-own-slider-1348x552.jpg"
        )

        // Add image URLs to sliderImage
        sliderImage.setItems(images)

        // Add slider component to a container
        frame_layout_container.addView(sliderImage)

        sliderImage.addTimerToSlide(3000)
        //sliderImage.removeTimerSlide()

        sliderImage.getItems()
        sliderImage.onPageListener(onPageScroll = { position, offSet, offSetPixels ->
            Log.d("A","position $position  offSet: $offSet  pixels $offSetPixels")
        }, onPageStateChange = { state ->
            Log.d("B","State change $state")
        }, onPageSelected = { position ->
            Log.d("C", "page select $position")
        })

        sliderImage.getIndicator()


        //Create tool bar
        var toolbar = tool_bar
        toolbar.title = "Category"
        setSupportActionBar(toolbar)

        //Get data for category recycler view
        getData()

        //Add data to grid recycler view
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