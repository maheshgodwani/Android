package com.example.loginapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        var etEmail :TextView = findViewById(R.id.etEmail);
        var etPassword : TextView = findViewById(R.id.etPassword);
        var btnLogin : Button = findViewById(R.id.btnLogin);
        var btnRegister : TextView = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener {
            var i = Intent(this,Register::class.java)
            startActivity(i)
        }

        btnLogin.setOnClickListener {
            Toast.makeText(applicationContext,"You are Login", Toast.LENGTH_LONG).show()
        }
    }
}