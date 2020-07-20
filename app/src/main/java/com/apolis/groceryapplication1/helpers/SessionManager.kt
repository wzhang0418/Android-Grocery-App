package com.apolis.groceryapplication1.helpers

import android.content.Context
import android.content.SharedPreferences

class SessionManager(val mContext: Context) {

    var sharedPreferences: SharedPreferences = mContext.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
    var editor: SharedPreferences.Editor = sharedPreferences.edit()

    companion object {
        const val FILE_NAME = "my_file"
        const val KEY_NAME = "name"
        const val KEY_EMAIL = "email"
        const val KEY_MOBILE = "mobile"
        const val KEY_PASSWORD = "password"
        const val KEY_IS_LOGGED_IN = "isLoggedIn"
    }

    fun register(name: String, email: String, mobile: String, password: String) {
        editor.putString(KEY_NAME, name)
        editor.putString(KEY_EMAIL, email)
        editor.putString(KEY_MOBILE, mobile)
        editor.putString(KEY_PASSWORD, password)
        editor.putBoolean(KEY_IS_LOGGED_IN, true)
        editor.commit()
    }

    fun saveLoginData(email: String, password: String){
        editor.putString(KEY_EMAIL, email)
        editor.commit()
        mContext.toast("data saved in share preference file")
    }

    fun getUser(): String? {
        return sharedPreferences.getString(KEY_NAME, null)
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