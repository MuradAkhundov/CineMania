package com.muradakhundov.cinemania.movie.util.carouselview

import android.view.View
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.abs

class CarouselViewSupporter : ViewPager2.PageTransformer {
    private var viewPager : ViewPager2? = null
    override fun transformPage(page: View, position: Float) {
        if (viewPager == null){
            viewPager = page.parent.parent as ViewPager2
            viewPager?.offscreenPageLimit = 2  // number of pages to cache
        }
        val width = page.width
        val margin = width * -0.5f
        page.translationX = -position * (width + margin)
        page.scaleX = 1f - abs(position) * 0.45f
        page.scaleY = 1f - abs(position) * 0.45f
        page.alpha = 1f - abs(position) * 0.5f
    }

}

