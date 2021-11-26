package com.skhproject.classifiedapp.db.dao

interface NetworkResult {

    fun loading(string: String?);
    fun success(classifiedResponse: Any?);
    fun failed(string: String?);
}