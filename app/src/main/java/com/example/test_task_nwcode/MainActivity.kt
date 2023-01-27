package com.example.test_task_nwcode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.test_task_nwcode.databinding.ActivityMainBinding
import com.example.test_task_nwcode.model.PixabayResponse
import com.example.test_task_nwcode.repository.RetrofitClient
import com.example.test_task_nwcode.viewmodel.PixabayViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: PixabayViewModel

    private lateinit var mapResponse: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        val navController = navHostFragment.navController

        NavigationUI.setupActionBarWithNavController(this, navController)

        println("228")

        getPixabayResponse()

        binding.textView.text = mapResponse
//        Log.d("yertyertyertyer", mapResponse)
        /*viewModel = ViewModelProvider(this)[PixabayViewModel::class.java]
        viewModel.getPixabayResponse()
        //binding.textView.text = viewModel.getPixabayResponse().toString()
        Log.d("BLABLA", viewModel.getPixabayResponse().toString())*/
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.nav_host_fragment_container)
        return navController.navigateUp()
    }

    fun getPixabayResponse() {
        mapResponse = "123"
        RetrofitClient.api.getAllData(
            "33106230-b104905cd7ff74ed17e2229af",
            "yellow+flowers",
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
                    mapResponse = response.body()!!.hits.toString()
                } else {
                    mapResponse = "null siooqa"
                    return
                }
            }

            override fun onFailure(call: Call<PixabayResponse>, t: Throwable) {
                mapResponse = "faillll"
                Log.d("qwerqwy", t.message.toString())
            }
        })
    }
}