package com.skhproject.classifiedapp.network

import com.skhproject.classifiedapp.model.ClassifiedResponse
import retrofit2.Call
import retrofit2.http.GET

interface IRestService {

    @GET("/default/dynamodb-writer")
    fun getListing(): Call<ClassifiedResponse>

}