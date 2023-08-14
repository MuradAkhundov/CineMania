package com.muradakhundov.cinemania.movie.util.authvalidation

import android.content.Context
import android.util.Patterns
import android.widget.EditText
import android.widget.Toast

class AuthenticationChecker {

    companion object{
         fun isValidSignUp(context : Context, name : EditText, email : EditText, password : EditText,confirmPassword : EditText): Boolean {
            if (name.text.toString().trim().isEmpty()) {
                showToast(context,"Name field cannot be blank!")
                return false
            } else if (email.text!!.toString().trim().isEmpty()) {
                showToast(context,"Email field cannot be blank ")
                return false
            } else if (!Patterns.EMAIL_ADDRESS.matcher(
                    email.text.toString().trim()
                ).matches()
            ) {
                showToast(context,"Email style is not valid")
                return false
            } else if (password.text.toString().trim().isEmpty()) {
                showToast(context,"password field cannot be empty")
                return false
            } else if (confirmPassword.text.toString().trim().isEmpty()) {
                showToast(context,"password confirm field cannot be empty")
                return false
            } else if (!password.text.toString().trim()
                    .equals(confirmPassword.text.toString())
            ) {
                showToast(context,"password fields should be equal")
                return false
            } else {
                return true
            }
        }

        fun isValidSignIn(context: Context,email : EditText, password : EditText): Boolean {
                if (!Patterns.EMAIL_ADDRESS.matcher(email.text).matches()) {
                    showToast(context,"Email style is not valid")
                    return false
                } else if (email.text.toString().trim().isEmpty()) {
                    showToast(context,"Email field cannot be blank ")
                    return false
                } else if (password.text.toString().trim().isEmpty()) {
                    showToast(context,"password field cannot be empty")
                    return false
                }
            return true
        }


        fun showToast(context : Context ,message : String){
            Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
        }
    }
}