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
        val fullnameEditText = findViewById<EditText>(R.id.fullnameEditText)
        val usernameEditText = findViewById<EditText>(R.id.usernameEditText)
        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val registerButton = findViewById<Button>(R.id.registerButton)

        // Set click listener for the Register Button
        registerButton.setOnClickListener {
            val fullname = fullnameEditText.text.toString().trim()
            val username = usernameEditText.text.toString().trim()
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (fullname.isEmpty() || username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            } else {
                // Send signup request in a separate thread
                Thread {
                    val response = SignUpRequest.sendSignUpRequest(fullname, username, email, password)
                    runOnUiThread {
                        Toast.makeText(this, response, Toast.LENGTH_SHORT).show()
                        if (response.contains("Sign Up Success")) {
                            // Navigate to Login Page
                            startActivity(Intent(this, MainActivity::class.java))
                            finish()
                        }
                    }
                }.start()
            }
        }
    }
}
