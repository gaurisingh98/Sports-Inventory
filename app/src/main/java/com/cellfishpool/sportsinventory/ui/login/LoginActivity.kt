package com.cellfishpool.sportsinventory.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cellfishpool.sportsinventory.databinding.ActivityLoginBinding
import com.cellfishpool.sportsinventory.ui.SignUpActivity
import com.cellfishpool.sportsinventory.ui.coach.CoachActivity
import com.cellfishpool.sportsinventory.ui.student.StudentActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupFirebaseAuth()
        addListeners()
    }

    private fun addListeners() {
        with(binding) {
            btnLogin.setOnClickListener {
                signIn(binding.etEmail.text.toString(), binding.etPassword.text.toString())
            }
            tvSignup.setOnClickListener {
                startActivity(Intent(this@LoginActivity, SignUpActivity::class.java))
            }
        }
    }

    private fun signIn(email: String, password: String) {
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this, OnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Successfully Logged In", Toast.LENGTH_LONG).show()
                    if (email != "coachadmin@iiml.ac.in")
                        startActivity(Intent(this, StudentActivity::class.java))
                    else startActivity(Intent(this, CoachActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "Login Failed", Toast.LENGTH_LONG).show()
                }
            })
    }

    private fun setupFirebaseAuth() {
        mAuth = FirebaseAuth.getInstance()
    }

    override fun onStart() {
        super.onStart()
        val currentUser = mAuth.currentUser
        if (currentUser != null)
            updateUI()
    }

    private fun updateUI() {
        startActivity(Intent(this, StudentActivity::class.java))
    }
}