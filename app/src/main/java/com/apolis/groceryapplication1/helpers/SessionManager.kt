package com.apolis.groceryapplication1.helpers

import android.content.Context
import android.content.SharedPreferences
import com.apolis.groceryapplication1.models.User

class SessionManager(val mContext: Context) {

    var sharedPreferences: SharedPreferences = mContext.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
    var editor: SharedPreferences.Editor = sharedPreferences.edit()

    companion object {
        const val FILE_NAME = "my_file"
        const val KEY_ID = "_id"
        const val KEY_NAME = "name"
        const val KEY_EMAIL = "email"
        const val KEY_MOBILE = "mobile"
        const val KEY_PASSWORD = "password"
        const val KEY_IS_LOGGED_IN = "isLoggedIn"

        const val KEY_RECEIVER_NAME = "receiverName"
        const val KEY_RECEIVER_MOBILE = "receiverMobile"
    }

    fun register(user: User) {
        editor.putString(KEY_ID, user._id)
        editor.putString(KEY_NAME, user.firstName)
        editor.putString(KEY_EMAIL, user.email)
        editor.putString(KEY_MOBILE, user.mobile)
        editor.putString(KEY_PASSWORD, user.password)
        editor.putBoolean(KEY_IS_LOGGED_IN, true)
        editor.commit()
    }

    fun saveLoginData(email: String){
        editor.putString(KEY_EMAIL, email)
        editor.commit()
        mContext.toast("data saved in share preference file")
    }

    fun saveReceiver(receiverName: String, receiverMobile: String){
        editor.putString(KEY_RECEIVER_NAME, receiverName)
        editor.putString(KEY_RECEIVER_MOBILE, receiverMobile)
        editor.commit()
        mContext.toast("Receiver info is stored in sharedPreference")
    }

    fun getUserName(): String? {
        return sharedPreferences.getString(KEY_NAME, null)
    }
    fun getUserId(): String? {
        return sharedPreferences.getString(KEY_ID, null)
    }
    fun getMobile(): String? {
        return sharedPreferences.getString(KEY_MOBILE, null)
    }

    fun isLoggedIn(): Boolean{
        return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false)
    }

    fun logout() {
        // will delete only name from sharePreference file
        //editor.remove(KEY_NAME)
        // will delete all sharePreference data
        editor.clear()
        editor.commit()

    }
}