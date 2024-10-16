package com.example.sharedpreference

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var edUserName : EditText = findViewById(R.id.edittext1)
        var edPassword : EditText = findViewById(R.id.edittext2)

        var btn_save : Button = findViewById(R.id.btnsave)
        var btn_read : Button = findViewById(R.id.btnread)

        var sp = application.getSharedPreferences("LoginData", Context.MODE_PRIVATE)
        var editor = sp.edit()

        btn_save.setOnClickListener {
            editor.putString("username",edUserName.text.toString())
            editor.putString("password",edPassword.text.toString())
            editor.commit()
            Toast.makeText(applicationContext,"Data Saved",Toast.LENGTH_LONG).show()
            edUserName.setText("")
            edPassword.setText("")
        }

        btn_read.setOnClickListener {
            edUserName.setText(sp.getString("username",""))
            edPassword.setText(sp.getString("password",""))
        }
    }
}