package com.black3.app.searchmovies.Utilities

import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast


fun isConnectedNetwork(context: Context): Boolean {
    
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    
    if (cm.activeNetworkInfo != null && cm.activeNetworkInfo.isConnected)
    {
        Toast.makeText(context,"Estás conectado a la red",Toast.LENGTH_LONG).show()
    }
    Toast.makeText(context,"No estás conectado a la red",Toast.LENGTH_LONG).show()
    return false
}