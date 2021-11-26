package com.skhproject.classifiedapp.listeners

import android.view.View
import com.skhproject.classifiedapp.db.entity.Listing

interface ClickListener {
    fun itemClick(item: Listing,view: View)
}