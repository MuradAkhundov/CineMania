package com.muradakhundov.cinemania.activity.onboarding.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.muradakhundov.cinemania.R
import com.muradakhundov.cinemania.activity.auth.activity.StartActivity
import com.muradakhundov.cinemania.databinding.ActivityMainBinding
import com.muradakhundov.cinemania.activity.onboarding.adapter.ViewPagerAdapter
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var adapter = ViewPagerAdapter(this)
        var dot = findViewById<DotsIndicator>(R.id.dotsIndicator)
        var nextButton = findViewById<ImageView>(R.id.button)
        binding.viewPager.adapter = adapter
        dot.setViewPager2(binding.viewPager)
        nextButton.setOnClickListener {
            val currentItem = binding.viewPager.currentItem
            val nextPage = currentItem + 1

            if (nextPage < adapter.itemCount) {
                binding.viewPager.setCurrentItem(
                    nextPage,
                    true
                                       )
            }
            else if(nextPage == adapter.itemCount){
                    startActivity(Intent(this@MainActivity,StartActivity::class.java))
            }
        }
    }
}