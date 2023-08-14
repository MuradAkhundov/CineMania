package com.muradakhundov.cinemania.movie.splash

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.muradakhundov.cinemania.movie.onboarding.activity.MainActivity
import com.muradakhundov.cinemania.databinding.ActivitySplashScreenBinding
import com.muradakhundov.cinemania.movie.home.activity.HomeActivity
import com.muradakhundov.cinemania.preferencemanager.PreferenceManager

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySplashScreenBinding
    private lateinit var preferenceManager: PreferenceManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        preferenceManager = PreferenceManager(this)


        binding.lottieanimation.playAnimation()
        Handler().postDelayed({
            if (preferenceManager.getEmailBoolean("isSet")){
                startActivity(Intent(this@SplashScreenActivity, HomeActivity::class.java))
            }
            else{
                startActivity(Intent(this@SplashScreenActivity,MainActivity::class.java))
            }
            binding.lottieanimation.pauseAnimation()

        },5000)
    }
}