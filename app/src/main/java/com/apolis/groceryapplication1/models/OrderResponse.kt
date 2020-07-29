package com.apolis.groceryapplication1.models

import java.io.Serializable

data class OrderResponse(
        val __v: Int,
        val _id: String,
        val date: String,
        val orderSummary: OrderSummary,
        val products: List<Product>,
        val shippingAddress: ShippingAddress,
        val user: User,
        val userId: String
):Serializable{
    companion object{
        const val KEY_ORDER = "orderResponse"
    }
}

data class OrderSummary(
<<<<<<< HEAD
        val deliveryCharges: Double? = null,
        val discount: Double? = null,
        val orderAmount: Double? = null,
        val ourPrice: Double? = null
=======
        val deliveryCharges: Int,
        val discount: Int,
        val orderAmount: Int,
        val ourPrice: Int
>>>>>>> 9d8b7eebba08273004465e73c702a9d3b1a4effa
):Serializable{
    companion object{
        const val KEY_ORDER_SUMMARY = "orderSummary"
    }
}
/*
data class Product(
    val _id: String,
    val image: String,
    val mrp: Int,
    val price: Int,
    val quantity: Int
)*/

data class ShippingAddress(
        val city: String,
        val houseNo: String,
        val pincode: Int,
        val streetName: String
):Serializable{
    companion object{
        const val KEY_SHIPPING_ADDRESS = "shippingAddress"
    }
}