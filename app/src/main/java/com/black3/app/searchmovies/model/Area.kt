package com.black3.app.searchmovies.model

class Area {
    
    private var id: Int = 0
    private var title: String? = null
    
    fun getId(): Int {
        return id
    }
    
    fun setId(id: Int) {
        this.id = id
    }
    
    fun getTitle(): String? {
        return title
    }
    
    fun setTitle(title: String) {
        this.title = title
    }
}