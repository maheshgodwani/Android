package com.example.registrationapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.core.widget.addTextChangedListener

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        var UserName : EditText = findViewById(R.id.editTextTextUserName)
        var Password : EditText = findViewById(R.id.editTextTextPassword)
        var email : EditText = findViewById(R.id.editTextTextEmailAddress)


        email.addTextChangedListener {object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (!)
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        }}
    }
}