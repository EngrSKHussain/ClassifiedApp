package com.skhproject.classifiedapp.listeners

import com.skhproject.classifiedapp.db.entity.Listing

interface ClickListener {
    fun itemClick(item: Listing)
}