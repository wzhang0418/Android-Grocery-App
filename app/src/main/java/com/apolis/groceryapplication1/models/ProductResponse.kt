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
    val __v: Int,
    val _id: String,
    val catId: Int,
    val created: String,
    val description: String,
    val image: String,
    val mrp: Double,
    val position: Int,
    val price: Double,
    val productName: String,
    val quantity: Int,
    val status: Boolean,
    val subId: Int,
    val unit: String
): Serializable{
    companion object{
        const val PRODUCT_KEY = "product"
    }
}