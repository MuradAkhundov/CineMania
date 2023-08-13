package com.muradakhundov.cinemania.activity.auth.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.muradakhundov.cinemania.R
import com.muradakhundov.cinemania.activity.home.activity.HomeActivity
import com.muradakhundov.cinemania.activity.util.authvalidation.AuthenticationChecker
import com.muradakhundov.cinemania.databinding.ActivitySignInBinding
import kotlin.math.log

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        firebaseAuth = FirebaseAuth.getInstance()

        binding.signInBtn.setOnClickListener {
            login()
        }

        binding.imageBack.setOnClickListener {
            super.onBackPressed()
        }
        setContentView(binding.root)
    }

    fun login() {
        if (AuthenticationChecker.isValidSignIn(this, binding.emailText, binding.passwordText)) {
            val email = binding.emailText.text.toString()
            val password = binding.passwordText.text.toString()

            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }.addOnFailureListener { exception ->
                Toast.makeText(applicationContext, exception.localizedMessage, Toast.LENGTH_LONG)
                    .show()
            }
        }
    }
}