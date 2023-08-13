package com.muradakhundov.cinemania.activity.auth.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.muradakhundov.cinemania.R
import com.muradakhundov.cinemania.activity.auth.activity.entity.Users
import com.muradakhundov.cinemania.activity.home.activity.HomeActivity
import com.muradakhundov.cinemania.databinding.ActivityStartBinding
class StartActivity : AppCompatActivity() {
    private lateinit var binding : ActivityStartBinding
    private lateinit var firebaseAuth : FirebaseAuth
    private lateinit var signInClient : GoogleSignInClient
    private lateinit var mDbRef : DatabaseReference
    val Req_Code: Int = 123
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityStartBinding.inflate(layoutInflater)

        FirebaseApp.initializeApp(this)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        signInClient = GoogleSignIn.getClient(this, gso)
        firebaseAuth = FirebaseAuth.getInstance()

        binding.signUpBtn.setOnClickListener {
            startActivity(Intent(this@StartActivity,SignUpActivity::class.java))
        }

        binding.signInText.setOnClickListener {
            startActivity(Intent(this@StartActivity,SignInActivity::class.java))
        }

        binding.signInViaGoogle.setOnClickListener {
            signInGoogle()
        }


        setContentView(binding.root)
    }


    private fun signInGoogle(){
        val signIntent : Intent = signInClient.signInIntent
        startActivityForResult(signIntent,Req_Code)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Req_Code){
            val task : Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleResult(task)
        }
    }

    private fun handleResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account: GoogleSignInAccount? = completedTask.getResult(ApiException::class.java)
            if (account != null) {
                UpdateUI(account)
            }
        } catch (e: ApiException) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun UpdateUI(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)

        firebaseAuth.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {

                addUserToDatabase(account.displayName!!,account.email,firebaseAuth.currentUser?.uid!!,"")
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun addUserToDatabase(name : String, email: String?, uid: String,image : String
    ) {
        mDbRef = FirebaseDatabase.getInstance().getReference()
        mDbRef.child("user").child(uid).setValue(Users(name,email!!,uid,image))
    }

}