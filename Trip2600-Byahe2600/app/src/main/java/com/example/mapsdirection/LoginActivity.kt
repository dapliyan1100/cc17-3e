package com.example.mapsdirection

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val loginButton = findViewById<Button>(R.id.btnLogin)
        val registerButton = findViewById<Button>(R.id.btnRegister)
// Set click listener for the login button
        loginButton.setOnClickListener {
            // Redirect to the Login Activity
            val loginIntent = Intent(this, MainActivity::class.java)
            startActivity(loginIntent)
        }

        // Set click listener for the register button
        registerButton.setOnClickListener {
            // Redirect to the Register Activity
            val registerIntent = Intent(this, RegisterActivity::class.java)
            startActivity(registerIntent)
        }
    }


}