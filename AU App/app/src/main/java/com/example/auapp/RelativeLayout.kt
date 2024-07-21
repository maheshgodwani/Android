package com.example.auapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import android.widget.ToggleButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RelativeLayout : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_relative_layout)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var btn: Button = findViewById(R.id.button)

        btn.setOnClickListener {
            Toast.makeText(applicationContext, "Button Clicked", Toast.LENGTH_LONG).show()
        }

        var imgbtn: ImageButton = findViewById(R.id.imageButton)

        imgbtn.setOnClickListener {
            Toast.makeText(applicationContext, "Download Started", Toast.LENGTH_LONG).show()
        }

        var tglbtn: ToggleButton = findViewById(R.id.toggleButton)
        var imgview: ImageView = findViewById(R.id.imageView)

        tglbtn.setOnClickListener {
            if (tglbtn.text.equals("OFF")) {
                imgview.setImageResource((R.drawable.on))
            } else {
                imgview.setImageResource(R.drawable.off)
            }
        }



    }
}