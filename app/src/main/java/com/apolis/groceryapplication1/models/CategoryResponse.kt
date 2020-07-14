package com.apolis.groceryapplication1.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CategoryResponse(
    val count: Int,
    @SerializedName("data")
    val categoryData: ArrayList<Category>,
    val error: Boolean
): Serializable

data class Category(
    val __v: Int,
    val _id: String,
    val catDescription: String,
    val catId: Int,
    val catImage: String,
    val catName: String,
    val position: Int,
    val slug: String,
    val status: Boolean
): Serializable{
    companion object{
        const val CATEGORY_KEY = "category"
    }
}