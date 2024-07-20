package com.example.relativelayout_alltypesofbutton

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import android.widget.ToggleButton
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btn : Button = findViewById(R.id.button)

        btn.setOnClickListener {
            Toast.makeText(applicationContext,"Button Clicked",Toast.LENGTH_LONG).show()
        }

        var imgbtn : ImageButton = findViewById(R.id.imageButton)

        imgbtn.setOnClickListener {
            Toast.makeText(applicationContext,"Download Started",Toast.LENGTH_LONG).show()
        }

        var tglbtn : ToggleButton = findViewById(R.id.toggleButton)
        var imgview : ImageView = findViewById(R.id.imageView)

        tglbtn.setOnClickListener {
            if(tglbtn.text.equals("OFF")){
                imgview.setImageResource((R.drawable.on))
            }
            else{
                imgview.setImageResource(R.drawable.off)
            }
        }


        var ch1 : CheckBox = findViewById(R.id.checkBox1)
        var ch2 : CheckBox = findViewById(R.id.checkBox2)
        var ch3 : CheckBox = findViewById(R.id.checkBox3)
        var text : TextView = findViewById(R.id.textView)

        ch1.setOnClickListener {
            var str = "JAVA : ${ch1.isChecked}\nKOTLIN : ${ch2.isChecked}\nANDROID : ${ch3.isChecked}"
            text.setText(str)
        }

        ch2.setOnClickListener {
            var str = "JAVA : ${ch1.isChecked}\nKOTLIN : ${ch2.isChecked}\nANDROID : ${ch3.isChecked}"
            text.setText(str)
        }

        ch3.setOnClickListener {
            var str = "JAVA : ${ch1.isChecked}\nKOTLIN : ${ch2.isChecked}\nANDROID : ${ch3.isChecked}"
            text.setText(str)
        }


        var rgp : RadioGroup = findViewById(R.id.radiogroup)
        var text2 : TextView = findViewById(R.id.textView2)

        rgp.setOnCheckedChangeListener { radioGroup, i ->
            var rbtn = findViewById<RadioButton>(i)
            if(rbtn!=null) {
                text2.setText(rbtn.text)
            }
        }


        var resetbtn : Button = findViewById(R.id.button2)

        resetbtn.setOnClickListener {
            rgp.clearCheck()
            text2.setText("Select Option")
        }


        var fab : FloatingActionButton = findViewById(R.id.floatingActionButton)
        fab.setOnClickListener {
            Toast.makeText(applicationContext,"Floating Action Button Clicked",Toast.LENGTH_LONG).show()
        }
     }
}