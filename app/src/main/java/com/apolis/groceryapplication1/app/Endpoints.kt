package com.apolis.groceryapplication1.app

class Endpoints {

    companion object{

        private const val URL_CATEGORY = "category"
        private const val URL_SUB_CATEGORY = "subcategory"
        private const val URL_PRODUCTS = "products"

        fun getCategory(): String{
            return Config.BASE_URL + URL_CATEGORY
        }

        fun getSubCategoryByCatId(catId: Int): String{
            return "${Config.BASE_URL + URL_SUB_CATEGORY}/$catId"
        }

        fun getProductsBySubId(subId: Int): String{
            return "${Config.BASE_URL + URL_PRODUCTS}/sub/$subId"
        }

        fun getImage(): String{
            return Config.IMAGE_URL
        }


        fun getRegisterURL(): String{
            return Config.REGISTER_URL
        }
        fun getLoginURL(): String{
            return Config.LOGIN_URL
        }
    }


}