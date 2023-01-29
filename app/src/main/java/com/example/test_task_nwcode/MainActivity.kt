package com.example.test_task_nwcode

import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET
import android.net.NetworkInfo
import android.net.NetworkRequest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.test_task_nwcode.databinding.ActivityMainBinding
import com.example.test_task_nwcode.model.PixabayResponse
import com.example.test_task_nwcode.repository.RetrofitClient
import com.example.test_task_nwcode.ui.NoInternetActivity
import com.example.test_task_nwcode.utils.InternetConnectionReceiver
import com.example.test_task_nwcode.viewmodel.PixabayViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity(), InternetConnectionReceiver.ConnectivityReceiverListener {
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

        registerReceiver(
            InternetConnectionReceiver(),
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )



        NavigationUI.setupActionBarWithNavController(this, navController)

    }

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        showNetworkMessage(isConnected)
    }

    override fun onResume() {
        super.onResume()
        InternetConnectionReceiver.connectivityReceiverListener = this
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.nav_host_fragment_container)
        return navController.navigateUp()
    }




    private fun startNoInternetActivity() {
        startActivity(Intent(this, NoInternetActivity::class.java))
        finish()

    }



    private fun showNetworkMessage(isConnected: Boolean) {
        if (!isConnected) {
            startNoInternetActivity()
        } else {
            Log.d("NETWORK STATUS", "CONNECTED TO THE INTERNET")
        }

    }
}