package com.black3.app.searchmovies.model

import android.provider.BaseColumns



class AreaContract {
    private fun AreaContract()  {}
    
    class AreaEntry : BaseColumns {
        companion object {
            val TABLE_NAME = "areas"
            
            val COLUMN_ID = "id"
            val COLUMN_TITLE = "title"
        }
    }
}