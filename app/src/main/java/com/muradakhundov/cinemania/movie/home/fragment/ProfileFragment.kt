package com.muradakhundov.cinemania.movie.home.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import com.muradakhundov.cinemania.R
import com.muradakhundov.cinemania.databinding.FragmentProfileBinding
import com.muradakhundov.cinemania.movie.auth.activity.SignUpActivity
import com.muradakhundov.cinemania.movie.auth.activity.StartActivity
import com.muradakhundov.cinemania.preferencemanager.PreferenceManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private lateinit var binding : FragmentProfileBinding
    private lateinit var preferenceManager : PreferenceManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(layoutInflater,container,false)

        preferenceManager = PreferenceManager(requireContext())
        var username : String? = preferenceManager.getUsername("username")
        var email : String? = preferenceManager.getEmail("email")

        binding.username.text = username
        binding.email.text = email


        binding.logout.setOnClickListener {
            preferenceManager.clear()
            startActivity(Intent(requireContext(),StartActivity::class.java))
        }
        return binding.root



    }


}