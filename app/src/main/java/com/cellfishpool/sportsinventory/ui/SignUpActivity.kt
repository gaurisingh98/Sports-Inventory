package com.cellfishpool.sportsinventory.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cellfishpool.sportsinventory.databinding.ActivitySignUpBinding
import com.cellfishpool.sportsinventory.ui.coach.CoachActivity
import com.cellfishpool.sportsinventory.ui.student.StudentActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        addListener()
    }

    private fun addListener() {
        with(binding) {
            btnSignup.setOnClickListener {
                pbLoader.visibility = View.VISIBLE
                signUp(etEmail.text.toString(), etPassword.text.toString())
            }
        }
    }

    private fun signUp(email: String, password: String) {
        auth = FirebaseAuth.getInstance()
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this, OnCompleteListener { task ->
                if (task.isSuccessful) {
                    binding.pbLoader.visibility = View.GONE
                    if (email == "coachadmin@iiml.ac.in") {
                        Toast.makeText(this, "Successfully Registered as Coach", Toast.LENGTH_LONG)
                            .show()
                        startActivity(Intent(this, CoachActivity::class.java))
                    } else {
                        Toast.makeText(
                            this,
                            "Successfully Registered as Student",
                            Toast.LENGTH_LONG
                        )
                            .show()
                        startActivity(Intent(this, StudentActivity::class.java))
                    }
                    finish()
                } else {
                    binding.pbLoader.visibility = View.GONE
                    binding.tvError.text = "Registration Failed"
                }
            })
    }
}