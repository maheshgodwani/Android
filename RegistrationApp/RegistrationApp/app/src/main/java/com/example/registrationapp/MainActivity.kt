package com.example.registrationapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var imgView : ImageView = findViewById(R.id.imageView)

        Thread(Runnable {
            Thread.sleep(3000)
            runOnUiThread {
                val intent = Intent(this, MainActivity2::class.java)
                startActivity(intent)
            }
        }).start()

    }
}