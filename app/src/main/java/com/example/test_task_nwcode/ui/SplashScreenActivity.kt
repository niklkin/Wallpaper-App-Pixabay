package com.example.test_task_nwcode.ui

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.test_task_nwcode.MainActivity
import com.example.test_task_nwcode.R
import com.example.test_task_nwcode.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivitySplashScreenBinding
    private val splashScreenTime = 1500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        runTimer()

//        Handler(Looper.myLooper()!!).postDelayed({
//            startActivity(Intent(this, MainActivity::class.java))
//            binding.progressBar.progress+=10
//            finish()
//        },5000)

    }

    private fun runTimer() {
        binding.progressBar.progress = 0
        val timer = object : CountDownTimer(splashScreenTime.toLong(), 100) {
            override fun onTick(millisUntilFinished: Long) {
                binding.progressBar.progress += (100 * 100) / splashScreenTime.toInt()
                Log.d("progress bar: ", binding.progressBar.progress.toString())
            }

            override fun onFinish() {
                binding.progressBar.progress = 100
                startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
                finish()
            }
        }
        timer.start()
    }

}