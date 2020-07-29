package com.apolis.groceryapplication1.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AddressResponse(
    val count: Int,
    @SerializedName("data")
    val address: List<Address>,
    val error: Boolean
)

data class Address(
    val __v: Int,
    val _id: String,
    val city: String,
    val houseNo: String,
    val pincode: Int,
    val streetName: String,
    val type: String,
    val userId: String
): Serializable {
    companion object{
        const val ADDRESS_KEY = "address"
    }
}