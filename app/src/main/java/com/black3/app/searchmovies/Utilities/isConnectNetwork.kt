package com.black3.app.searchmovies.Utilities

import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast


fun isConnectedNetwork(context: Context): Boolean {
    
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    
    if (cm.activeNetworkInfo != null && cm.activeNetworkInfo.isConnected)
    {
        if (cm.isDefaultNetworkActive)
        Toast.makeText(context,"Estás conectado a Internet",Toast.LENGTH_LONG).show()
    }else
    Toast.makeText(context,"No estás conectado a la red",Toast.LENGTH_LONG).show()
    return false
}