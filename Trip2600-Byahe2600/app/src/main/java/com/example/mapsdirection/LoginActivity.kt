package com.example.mapsdirection

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    lateinit var edtEmail: EditText
    lateinit var edtPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        edtEmail = findViewById(R.id.edtEmail)
        edtPassword = findViewById(R.id.edtPassword)

//        FirebaseApp.initializeApp(this)
//        auth = FirebaseAuth.getInstance()

        val loginButton = findViewById<Button>(R.id.btnLogin)
        val registerButton = findViewById<Button>(R.id.btnGoRegister)

        loginButton.setOnClickListener {
            val registerIntent = Intent(this, MainActivity::class.java)
            startActivity(registerIntent)
        }

        registerButton.setOnClickListener {
            // Redirect to the Register Activity
            val registerIntent = Intent(this, RegisterActivity::class.java)
            startActivity(registerIntent)
        }
    }

//    private fun loginUser() {
//        // Authenticate user with email and password
//        val email = edtEmail.text.toString()
//        val pass = edtPassword.text.toString()
//        auth.signInWithEmailAndPassword(email, pass)
//            .addOnCompleteListener(this) { task ->
//                if (task.isSuccessful) {
//                    // Redirect to the desired activity after successful login
//                    val loginIntent = Intent(this, MainActivity::class.java)
//                    startActivity(loginIntent)
//                    finish() // Close the LoginActivity
//                } else {
//                    // Handle different exceptions here
//                    val errorMsg = when (task.exception?.message) {
//                        "ERROR_INVALID_EMAIL" -> "Invalid email format."
//                        "ERROR_WRONG_PASSWORD" -> "Incorrect password."
//                        "ERROR_USER_NOT_FOUND" -> "User not found."
//                        // Handle other cases or provide a generic message
//                        else -> "Login failed. Please check your credentials."
//                    }
//                    Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show()
//                }
//            }
//    }
}
