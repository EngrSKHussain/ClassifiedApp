package com.skhproject.classifiedapp.model

import Pagination
import ListingItem

data class ClassifiedResponse(

    val results: List<ListingItem>,
    val pagination: Pagination

)