package com.cellfishpool.sportsinventory.ui.splash

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import com.cellfishpool.sportsinventory.ui.login.LoginActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val i = Intent(this, LoginActivity::class.java)
        startActivity(i)
        finish()
    }
}