package com.muradakhundov.cinemania.movie.home.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.muradakhundov.cinemania.movie.home.adapter.CarouselAdapter
import com.muradakhundov.cinemania.movie.home.viewmodel.HomeViewModel
import com.muradakhundov.cinemania.databinding.FragmentHomeBinding
import com.muradakhundov.cinemania.movie.home.adapter.FilmAdapter
import com.muradakhundov.cinemania.movie.home.adapter.GenreAdapter
import com.muradakhundov.cinemania.movie.home.model.Genre
import com.muradakhundov.cinemania.movie.util.carouselview.CarouselViewSupporter
import com.muradakhundov.cinemania.preferencemanager.PreferenceManager
import dagger.hilt.android.AndroidEntryPoint
import java.util.Timer
import java.util.TimerTask

@AndroidEntryPoint

class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding
    private lateinit var viewmodel : HomeViewModel
    private lateinit var preferenceManager: PreferenceManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentHomeBinding.inflate(inflater,container,false)
        Log.e("genre","Fragment")

        viewmodel.popularMovie.observe(viewLifecycleOwner){ movie ->
            val adapter = CarouselAdapter(requireContext(),movie.results)
            binding.viewpagerpopular.adapter = adapter
//            binding.viewpagerpopular.setPageTransformer(CarouselViewSupporter())
//            startTimer(adapter)
        }

        viewmodel.upcomingMovie.observe(viewLifecycleOwner){upcoming ->
            val adapter = FilmAdapter(requireContext(),upcoming.results)
            binding.recyclerUpcoming.adapter = adapter
        }

        viewmodel.nowplayingMovie.observe(viewLifecycleOwner){ nowplaying ->
            val adapter = FilmAdapter(requireContext(),nowplaying.results)
            binding.recyclerNowPlaying.adapter = adapter
        }
        viewmodel.topratedMovie.observe(viewLifecycleOwner){ toprated ->
            val adapter = FilmAdapter(requireContext(),toprated.results)
            binding.recyclerTopRated.adapter = adapter
        }

//        viewmodel.genreList.observe(viewLifecycleOwner){ genre ->
//            val adapter = GenreAdapter(requireContext(), genre.genres)
//            binding.recyclerCategory.adapter = adapter
//        }

        val tempGenreList = listOf(
            Genre(1,"action"),
            Genre(2,"comedy"),
            Genre(3,"thriller"),
            Genre(4,"crime"),
            Genre(5,"comedy"))

        val genreAdapter = GenreAdapter(requireContext(),tempGenreList)
        binding.recyclerCategory.adapter = genreAdapter
        preferenceManager = PreferenceManager(requireContext())
        var username : String? = preferenceManager.getUsername("username")
        binding.username.setText( "Hello , $username")
        return binding.root


    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempviewmodel : HomeViewModel by viewModels()
        viewmodel = tempviewmodel
    }

//    private fun startTimer(adapter: CarouselAdapter) {
//        val timer = Timer()
//        timer.scheduleAtFixedRate(object : TimerTask() {
//            override fun run() {
//                binding.viewpagerpopular.post {
//                    val currentItem = binding.viewpagerpopular.currentItem
//                    binding.viewpagerpopular.setCurrentItem(
//                        if (currentItem == adapter.itemCount - 1) 0 else currentItem + 1,
//                        true
//                    )
//                }
//            }
//        }, 5000, 5000)
//    }
}