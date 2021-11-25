package com.skhproject.classifiedapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RestService {

    private const val BASE_URL = "https://ey3f2y0nre.execute-api.us-east-1.amazonaws.com"

    private fun retrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

    val listingService: IRestService by lazy {
        retrofit().create(IRestService::class.java)
    }


}