package com.muradakhundov.cinemania.activity.splash

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.muradakhundov.cinemania.R
import com.muradakhundov.cinemania.activity.onboarding.activity.MainActivity
import com.muradakhundov.cinemania.databinding.ActivitySplashScreenBinding

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.lottieanimation.playAnimation()
        Handler().postDelayed({
            startActivity(Intent(this@SplashScreenActivity,MainActivity::class.java))
            binding.lottieanimation.pauseAnimation()
        },5000)
    }
}