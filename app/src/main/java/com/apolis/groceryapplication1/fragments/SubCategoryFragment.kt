package com.apolis.groceryapplication1.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.apolis.groceryapplication1.R
import com.apolis.groceryapplication1.adapters.AdapterProduct
import com.apolis.groceryapplication1.app.Endpoints
import com.apolis.groceryapplication1.models.Product
import com.apolis.groceryapplication1.models.ProductResponse
import com.apolis.groceryapplication1.models.SubCategory
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.fragment_product.view.*

class SubCategoryFragment : Fragment() {
    private var subId: Int = 0
    private lateinit var adapterProduct: AdapterProduct
    private var mList: ArrayList<Product> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            subId = it.getInt(SubCategory.SUBCATEGORY_KEY)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_product, container, false)
        Toast.makeText(activity!!, subId.toString(), Toast.LENGTH_LONG).show()

        init(view)

        return view
    }

    private fun init(view: View) {

        getData(subId, view)

    }

    private fun getData(subId: Int, view: View) {
//        Log.d("GETPRODUCTBUSUBID",Endpoints.getProductsBySubId(subId))

        var requestQueue = Volley.newRequestQueue(activity as Context)
        var request = StringRequest(
            Request.Method.GET,
            Endpoints.getProductsBySubId(subId),
            Response.Listener {
                //progress_bar.visibility = View.GONE
                var gson = GsonBuilder().create()
                var productResponse = gson.fromJson(it, ProductResponse::class.java)
                Log.d("Q", productResponse.toString())
                mList = productResponse.productData

                view.recycler_view_linear.layoutManager = LinearLayoutManager(activity!!)
                adapterProduct = AdapterProduct(activity!!, mList)
                adapterProduct.setData(mList)
                view.recycler_view_linear.adapter = adapterProduct
            },

            Response.ErrorListener {
                Log.d("ABC",it.message.toString())
            }
        )
        requestQueue.add(request)
    }


    companion object {
        @JvmStatic
        fun newInstance(subId: Int) =
            SubCategoryFragment().apply {
                arguments = Bundle().apply {
                    putInt(SubCategory.SUBCATEGORY_KEY, subId)
                }
            }
    }
}