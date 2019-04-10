package com.example.aml.photoapp.api

import com.example.aml.photoapp.models.PhotoList
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PhotoReteriver {

    private val service : PhotoApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://pixabay.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(PhotoApi::class.java)
    }

    fun getPhotos (callback: Callback<PhotoList>){

        val call = service.getPhotos()
        call.enqueue(callback)

    }
}