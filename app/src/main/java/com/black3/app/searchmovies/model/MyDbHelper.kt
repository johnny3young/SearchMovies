package com.black3.app.searchmovies.model

import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import com.black3.app.searchmovies.model.AreaContract.AreaEntry
import android.database.sqlite.SQLiteDatabase

 class MyDbHelper(context: Context):SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

override fun onCreate(db:SQLiteDatabase) {
db.execSQL(
    "CREATE TABLE " + AreaEntry.TABLE_NAME + " (" +
    AreaEntry.COLUMN_ID +
    AreaEntry.COLUMN_TITLE + " TEXT)"
)
}

override fun onUpgrade(db:SQLiteDatabase, oldVersion:Int, newVersion:Int) {
db.execSQL("DROP TABLE IF EXISTS " + AreaEntry.TABLE_NAME)
onCreate(db)
}

companion object {
private val DATABASE_VERSION = 1
private val DATABASE_NAME = "pym.db"
}
}