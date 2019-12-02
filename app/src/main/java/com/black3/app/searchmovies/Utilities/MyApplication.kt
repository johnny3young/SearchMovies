package com.black3.app.searchmovies.utilities

import android.app.Application

class MyApplication: Application (){
    
    override fun onCreate() {
        super.onCreate()
        instance = this
    }
    
    fun setConnectionListener(listener: ConnectionReceiver.ConnectionReceiverListener){
        ConnectionReceiver.connectionReceiverListener = listener
    }
    
    companion object{
        @get:Synchronized
        lateinit var instance: MyApplication
    }
    
}