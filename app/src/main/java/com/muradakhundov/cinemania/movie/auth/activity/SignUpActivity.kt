package com.muradakhundov.cinemania.movie.auth.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.muradakhundov.cinemania.movie.auth.activity.entity.Users
import com.muradakhundov.cinemania.movie.home.activity.HomeActivity
import com.muradakhundov.cinemania.movie.util.authvalidation.AuthenticationChecker
import com.muradakhundov.cinemania.databinding.ActivitySignUpBinding
import com.muradakhundov.cinemania.preferencemanager.PreferenceManager

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var mDbRef : DatabaseReference
    private lateinit var preferenceManager: PreferenceManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        preferenceManager = PreferenceManager(this)
        firebaseAuth = FirebaseAuth.getInstance()
        binding.signUpBtn.setOnClickListener {
            signUp()
        }

        binding.imageBack.setOnClickListener {
            super.onBackPressed()
        }

    }

    override fun onBackPressed() {}

    fun signUp() {
        if (AuthenticationChecker.isValidSignUp(
                applicationContext,
                binding.usernameText,
                binding.emailText,
                binding.passwordText,
                binding.confirmPasswordText
            )
        ) {
            val email = binding.emailText.text.toString()
            val password = binding.passwordText.text.toString()
            val uid = firebaseAuth.uid
            val name = binding.usernameText.text.toString()
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val intent = Intent(this, HomeActivity::class.java)
                        startActivity(intent)
                        addUserToDatabase(name,email,uid!!,"")
                        preferenceManager.setEmail("email",email)
                        preferenceManager.setUsername("username", name)
                        preferenceManager.setEmailBoolean("isSet",true)
                        finish()

                    }
                }.addOnFailureListener { exception ->
                Toast.makeText(applicationContext, exception.localizedMessage, Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
    private fun addUserToDatabase(name : String, email: String?, uid: String,image : String
    ) {
        mDbRef = FirebaseDatabase.getInstance().getReference()
        mDbRef.child("user").child(uid).setValue(Users(name,email!!,uid,image))
    }
}