package com.example.aml.photoapp.api

import android.provider.ContactsContract
import com.example.aml.photoapp.models.PhotoList
import retrofit2.Call
import retrofit2.http.GET

interface PhotoApi {

    @GET("?key=11947220-ce1e4f4bc73296ec5a6ea39e5&q=nature&image_type=nature")
    fun getPhotos () : Call<PhotoList>
}