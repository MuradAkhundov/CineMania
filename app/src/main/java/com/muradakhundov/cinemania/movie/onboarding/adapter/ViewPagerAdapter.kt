package com.muradakhundov.cinemania.movie.onboarding.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.muradakhundov.cinemania.movie.onboarding.fragment.OnBoardingFragment
import com.muradakhundov.cinemania.movie.onboarding.fragment.SecondOnBoardingFragment
import com.muradakhundov.cinemania.movie.onboarding.fragment.ThirdOnBoardingFragment
import java.lang.IllegalArgumentException

class ViewPagerAdapter(fa : FragmentActivity) : FragmentStateAdapter(fa) {

    companion object{
        private const val NUM_PAGES = 3
    }

    override fun getItemCount(): Int {
        return NUM_PAGES
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> OnBoardingFragment()
            1 -> SecondOnBoardingFragment()
            2 -> ThirdOnBoardingFragment()
            else -> throw IllegalArgumentException("Invalid position")
        }
    }
}