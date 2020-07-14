package com.apolis.groceryapplication1.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.apolis.groceryapplication1.R
import com.apolis.groceryapplication1.app.Endpoints
import com.apolis.groceryapplication1.models.Product
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_product_detail.*
import kotlinx.android.synthetic.main.row_category_adapter.*
import kotlinx.android.synthetic.main.row_category_adapter.view.*
import kotlinx.android.synthetic.main.row_category_adapter.view.image_view_category

class ProductDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        var product = intent.getSerializableExtra(Product.PRODUCT_KEY) as Product
        text_view_product_name.text = product.productName
        text_view_product_price.text = product.price.toString()

        Picasso.get()
            .load(Endpoints.getImage() + product.image)
            .placeholder(R.drawable.place_holder)
            .error(R.drawable.no_image)
            .into(image_view_category)
    }
}