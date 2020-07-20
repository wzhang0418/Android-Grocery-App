package com.apolis.groceryapplication1.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ProductResponse(
    val count: Int,
    @SerializedName("data")
    val productData: ArrayList<Product>,
    val error: Boolean
): Serializable

data class Product(
        val _id: String? = null,
        val catId: Int? = null,
        val description: String? = null,
        val image: String? = null,
        val mrp: Double?  = null,
        val price: Double? = null,
        val productName: String? = null,
        var qty: Int = 0,
        val subId: Int? = null,
        val unit: String? = null
): Serializable{
    companion object{
        const val PRODUCT_KEY = "product"
    }
}