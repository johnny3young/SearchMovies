package com.black3.app.searchmovies.Utilities

import android.content.Context
import android.graphics.Color
import android.net.ConnectivityManager
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.black3.app.searchmovies.R

fun isConnectedNetwork(context: Context, button: Button): Boolean {
    
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    
    if (cm.activeNetworkInfo != null && cm.activeNetworkInfo.isConnected) {
        if (cm.isDefaultNetworkActive) {
            Toast.makeText(context, "Estás conectado a Internet", Toast.LENGTH_LONG).show()
            button.visibility = View.VISIBLE
            
        }
    } else
    {
        Toast.makeText(context, "No estás conectado a la red", Toast.LENGTH_LONG).show()
        button.text = "Off"
        button.setBackgroundResource(R.drawable.rounded_button_off)
    }
    
    return false
    
}