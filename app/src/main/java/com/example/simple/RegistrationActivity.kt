package com.example.simple

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegistrationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration) // Link to the XML layout

        // Find views by ID
        val nameEditText = findViewById<EditText>(R.id.nameEditText)
        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val passwordEditText = findViewById<EditText>(R.id.registrationPasswordEditText)
        val registerButton = findViewById<Button>(R.id.registerButton)

        // Set click listener for the Register Button
        registerButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Registration Successful!", Toast.LENGTH_SHORT).show()

                // Navigate back to Login Page
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish() // Close RegistrationActivity so user can't go back to it using the back button
            }
        }
    }
}


