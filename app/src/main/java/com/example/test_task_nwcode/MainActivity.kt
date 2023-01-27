package com.example.test_task_nwcode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
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

    private var mapResponse: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        val navController = navHostFragment.navController

        NavigationUI.setupActionBarWithNavController(this, navController)

        getPixabayResponse()


    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.nav_host_fragment_container)
        return navController.navigateUp()
    }

    fun getPixabayResponse() {
        //mapResponse = "123"
        RetrofitClient.api.getAllData(
            "33106230-b104905cd7ff74ed17e2229af",
            "anime",
            "photo"
        ).enqueue(object :
            Callback<PixabayResponse> {

            override fun onResponse(
                call: Call<PixabayResponse>,
                response: Response<PixabayResponse>
            ) {
                if (response.body() != null) {
                    Log.d("7456745674567", response.body()!!.hits.toString())
                    //mapResponse = response.body()!!.hits.toString()
                    //binding.textView.text = mapResponse.toString()
                } else {
                    return
                }
            }

            override fun onFailure(call: Call<PixabayResponse>, t: Throwable) {
                //mapResponse = "faillll"
                Log.d("qwerqwy", t.message.toString())
            }
        })
    }
}