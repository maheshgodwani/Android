package com.example.auapp

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.CalendarView
import android.widget.EditText
import android.widget.MultiAutoCompleteTextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LineareLayout : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_lineare_layout)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        var edEmail : EditText = findViewById(R.id.editTextTextEmailAddress)

        edEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(char: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(!android.util.Patterns.EMAIL_ADDRESS.matcher(char).matches()){
                    edEmail.setError("Invalid Input!!")
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })


        var actvCity : AutoCompleteTextView = findViewById(R.id.autoCompleteTextView)
        var city = arrayOf("Morbi","Rajkot","Surat","Ahmedabad","Anand","Baroda","Jamanagar")
        var adapter =  ArrayAdapter<String>(this,android.R.layout.simple_selectable_list_item,city)
        actvCity.setAdapter(adapter)

        var mactvSkill : MultiAutoCompleteTextView = findViewById(R.id.multiAutoCompleteTextView)
        var skill= arrayOf("Web Designer","Web Developer", "SEO", "Android Developer", "Flutter Developer", "FullStack Developer","MERN Stack Developer")
        var skillAdapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_checked,skill)
        mactvSkill.setAdapter(skillAdapter)
        mactvSkill.setTokenizer(MultiAutoCompleteTextView.CommaTokenizer())



        var edDate : EditText = findViewById(R.id.editTextDate)
        var cal = Calendar.getInstance()
        var edTime : EditText = findViewById(R.id.editTextTime)

        edDate.setOnClickListener {
            DatePickerDialog(this, DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
                edDate.setText("$day/${month-1}/$year") },
                cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show()
        }

        edTime.setOnClickListener {
            TimePickerDialog(this,TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                edTime.setText("$hour : $minute")},
                cal.get(Calendar.HOUR), cal.get(Calendar.MINUTE), true).show()
        }


        var btn : Button = findViewById(R.id.button)

        btn.setOnClickListener {
         Toast.makeText(applicationContext,"Details Submitted", Toast.LENGTH_LONG).show()
        }

    }
}