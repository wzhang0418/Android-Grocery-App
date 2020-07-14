package com.apolis.groceryapplication1.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SubCategoryResponse(
    val count: Int,
    @SerializedName("data")
    val subCategoryData: ArrayList<SubCategory>,
    val error: Boolean
): Serializable

data class SubCategory(
    val __v: Int,
    val _id: String,
    val catId: Int,
    val position: Int,
    val status: Boolean,
    val subDescription: String,
    val subId: Int,
    val subImage: String,
    val subName: String
): Serializable{
    companion object{
        const val SUBCATEGORY_KEY = "subcategory"
    }
}