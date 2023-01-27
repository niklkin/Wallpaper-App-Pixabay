package com.example.test_task_nwcode.repository

import android.accessibilityservice.GestureDescription.StrokeDescription
import androidx.viewbinding.BuildConfig
import com.example.test_task_nwcode.model.PixabayResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface PixabayApi {


    @GET("api/")
    fun getAllData(
        @Query("key") api_key: String,
        @Query("q") q: String,
        @Query("image_type") image_type: String,

        ): Call<PixabayResponse>


}
