package com.apolis.groceryapplication1.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AddressResponse(
    val count: Int,
    @SerializedName("data")
    val address: ArrayList<Address>,
    val error: Boolean
): Serializable

data class Address(
<<<<<<< HEAD
        val __v: Int? = null,
        val _id: String? = null,
        var city: String? = null,
        var houseNo: String? = null,
        var pincode: Int? = null,
        var streetName: String? = null,
        val type: String? = null,
        val userId: String? = null
=======
    val __v: Int? = null,
    val _id: String? = null,
    val city: String? = null,
    val houseNo: String? = null,
    val pincode: Int? = null,
    val streetName: String? = null,
    val type: String? = null,
    val userId: String? = null
>>>>>>> 9d8b7eebba08273004465e73c702a9d3b1a4effa
): Serializable {
    companion object{
        const val ADDRESS_KEY = "address"
    }
}