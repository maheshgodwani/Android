package com.example.framelayout_pb_sb_rb

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.widget.RatingBar
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        var rb : RatingBar = findViewById(R.id.ratingBar2)
        var tv1 : TextView = findViewById(R.id.textView4)

        rb.setOnRatingBarChangeListener { ratingBar, fl, b ->
            tv1.setText("RatingBar : $fl")
        }

        var sb : SeekBar = findViewById(R.id.seekBar2)
        var tv2 : TextView = findViewById(R.id.textView5)

        sb.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                tv2.setText("SeekBar Value : $p1")
                sb.secondaryProgress = p1 + 5
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }
        })

    }
}
