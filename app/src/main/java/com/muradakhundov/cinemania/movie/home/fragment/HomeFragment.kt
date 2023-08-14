package com.muradakhundov.cinemania.movie.home.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.muradakhundov.cinemania.movie.home.adapter.CarouselAdapter
import com.muradakhundov.cinemania.movie.home.viewmodel.HomeViewModel
import com.muradakhundov.cinemania.databinding.FragmentHomeBinding
import com.muradakhundov.cinemania.preferencemanager.PreferenceManager
import dagger.hilt.android.AndroidEntryPoint

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

        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)

        viewmodel.popularMovie.observe(viewLifecycleOwner){ movie ->
            val adapter = CarouselAdapter(requireContext(),movie.results)
            binding.recyclerpopular.adapter = adapter

        }
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

}