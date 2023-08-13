package com.muradakhundov.cinemania.activity.auth.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.muradakhundov.cinemania.R
import com.muradakhundov.cinemania.activity.auth.activity.entity.Users
import com.muradakhundov.cinemania.activity.home.activity.HomeActivity
import com.muradakhundov.cinemania.activity.util.authvalidation.AuthenticationChecker
import com.muradakhundov.cinemania.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var mDbRef : DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
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