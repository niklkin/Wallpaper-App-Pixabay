package com.example.test_task_nwcode.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.test_task_nwcode.model.Hit
import com.example.test_task_nwcode.model.PixabayResponse
import com.example.test_task_nwcode.repository.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PixabayViewModel : ViewModel() {
    private var pixabayResponseLiveData = MutableLiveData<List<Hit>>()


    fun getPixabayResponse() {

        RetrofitClient.api.getAllData(
            "33106230-b104905cd7ff74ed17e2229af",
            "moon",
            "photo"
        ).enqueue(object :
            Callback<PixabayResponse> {

            override fun onResponse(
                call: Call<PixabayResponse>,
                response: Response<PixabayResponse>
            ) {
                if (response.body() != null) {
                    println(response.body()!!.hits.toString())
                    Log.d("yertyertyertyer", response.body()!!.hits.toString())
                    pixabayResponseLiveData.value = response.body()!!.hits
                } else {
                    //println(response.body()!!.hits.toString())
                    return
                }
            }

            override fun onFailure(call: Call<PixabayResponse>, t: Throwable) {
                Log.d("qwerqwy", t.message.toString())
            }
        })
    }

    fun observeMovieLiveData(): LiveData<List<Hit>> {
        return pixabayResponseLiveData
    }
}