package com.example.framelayout_pb_sb_rb

import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var pb : ProgressBar = findViewById(R.id.progressBar)


        pb.progressDrawable?.colorFilter = PorterDuffColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_IN)
        Thread(Runnable {
            var count = 0
            while (count <= 100){
                Thread.sleep(100)
                count++
                pb.setProgress(count)
                pb.secondaryProgress = count + 5
            }

            if(count>=100){
                var i = Intent(this,MainActivity2::class.java)
                startActivity(i)
            }
        }).start()

    }
}