package com.muradakhundov.cinemania.movie.auth.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.muradakhundov.cinemania.movie.home.activity.HomeActivity
import com.muradakhundov.cinemania.movie.util.authvalidation.AuthenticationChecker
import com.muradakhundov.cinemania.databinding.ActivitySignInBinding
import com.muradakhundov.cinemania.preferencemanager.PreferenceManager

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var preferenceManager : PreferenceManager
    private lateinit var mDbRef : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        preferenceManager = PreferenceManager(this)
        firebaseAuth = FirebaseAuth.getInstance()
        mDbRef = FirebaseDatabase.getInstance().getReference()

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
            val uid = firebaseAuth.uid
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    var usernameRef  = mDbRef.child("user").child(uid!!).child("name")
                    usernameRef.addListenerForSingleValueEvent(object : ValueEventListener {
                        override fun onDataChange(dataSnapshot: DataSnapshot) {
                            val username = dataSnapshot.getValue(String::class.java)
                            if (username != null) {
                                preferenceManager.setUsername("username", username)
                            }
                            preferenceManager.setEmail("email", email)
                            preferenceManager.setEmailBoolean("isSet",true)
                            val intent = Intent(this@SignInActivity, HomeActivity::class.java)
                            startActivity(intent)
                            finish()
                        }

                        override fun onCancelled(databaseError: DatabaseError) {
                            // Handle any errors that occur.
                        }
                    })
                }
            }.addOnFailureListener { exception ->
                Toast.makeText(applicationContext, exception.localizedMessage, Toast.LENGTH_LONG)
                    .show()
            }
        }
        }
    }
