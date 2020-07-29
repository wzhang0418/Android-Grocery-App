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
        val __v: Int? = null,
        val _id: String? = null,
        var city: String? = null,
        var houseNo: String? = null,
        var pincode: Int? = null,
        var streetName: String? = null,
        val type: String? = null,
        val userId: String? = null
): Serializable {
    companion object{
        const val ADDRESS_KEY = "address"
    }
}