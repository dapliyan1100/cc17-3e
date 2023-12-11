package com.example.mapsdirection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {
    lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnRegister: Button
//    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        edtEmail = findViewById(R.id.edtEmail)
        edtPassword = findViewById(R.id.edtPassword)
        btnRegister = findViewById(R.id.btnRegister)
//        FirebaseApp.initializeApp(this)
//        auth = FirebaseAuth.getInstance()


//        btnRegister.setOnClickListener {
//            signUpUser()
//        }
    }
//    private fun signUpUser() {
//        val email = edtEmail.text.toString()
//        val pass = edtPassword.text.toString()
//        // check pass
//        if (email.isBlank() || pass.isBlank()) {
//            Toast.makeText(this, "Email and Password can't be blank", Toast.LENGTH_SHORT).show()
//            return
//        }
//
//        auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(this) {
//            if (it.isSuccessful) {
//                Toast.makeText(this, "Singed Up Successful!", Toast.LENGTH_SHORT).show()
//                finish()
//            } else {
//                Toast.makeText(this, "Singed Up Failed!", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
}