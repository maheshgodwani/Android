package com.example.auapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RelativeLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var btn1 : Button = findViewById(R.id.button1)
        var btn2 : Button = findViewById(R.id.button2)
        var btn3 : Button = findViewById(R.id.button3)
        var btn4 : Button = findViewById(R.id.button4)

        btn1.setOnClickListener {
            var i = Intent(this,ConstraintLayout::class.java)
            startActivity(i)
        }

        btn2.setOnClickListener {
            var i = Intent(this,LineareLayout::class.java)
            startActivity(i)
        }

        btn3.setOnClickListener {
            var i = Intent(this,RelativeLayout::class.java)
            startActivity(i)
        }

        btn4.setOnClickListener {
            var i = Intent(this,FrameLayout::class.java)
            startActivity(i)
        }


    }
}