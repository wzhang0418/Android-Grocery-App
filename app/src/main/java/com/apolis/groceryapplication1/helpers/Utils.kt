package com.apolis.groceryapplication1.helpers

import android.content.Context
<<<<<<< HEAD
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
=======
import android.widget.Toast
>>>>>>> 9d8b7eebba08273004465e73c702a9d3b1a4effa

fun Context.toast(message: String){
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

<<<<<<< HEAD
fun Context.snackBar(view: View, message: String){
    Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
}

fun Context.snackBarCallBack(view: View){
    Snackbar.make(view, "You really want to delete", Snackbar.LENGTH_LONG)
            .setAction("UNDO", object: View.OnClickListener{
                override fun onClick(p0: View?) {
                    Snackbar.make(view, "Data is restored", Snackbar.LENGTH_LONG).show()
                }
            })
            .show()
}
=======
>>>>>>> 9d8b7eebba08273004465e73c702a9d3b1a4effa
