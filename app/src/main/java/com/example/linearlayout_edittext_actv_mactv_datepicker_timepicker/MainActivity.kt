package com.example.linearlayout_edittext_actv_mactv_datepicker_timepicker

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.MultiAutoCompleteTextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var edEmail : EditText = findViewById(R.id.editTextTextEmailAddress)
        edEmail.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(char: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(!android.util.Patterns.EMAIL_ADDRESS.matcher(char).matches()){
                    edEmail.setError("Invalid Input")
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })


        var actvCity : AutoCompleteTextView = findViewById(R.id.autoCompleteTextView)
        var city = arrayOf("Morbi", "Baroda", "Surat", "Anand", "Rajkot")
        var adapter = ArrayAdapter<String>(this,android.R.layout.simple_selectable_list_item, city)
//        Custome Layout
//        var adapter = ArrayAdapter<String>(this,R.layout.my_layout, city)
        actvCity.setAdapter(adapter)

        var mactvSkill : MultiAutoCompleteTextView = findViewById(R.id.multiAutoCompleteTextView)
        var skill = arrayOf("SEO", "Web Design", "Web Developer", "App Developer", "Graphic Designer", "Flutter Developer")
        var skillAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_checked, skill)
        mactvSkill.setAdapter(skillAdapter)
        mactvSkill.setTokenizer(MultiAutoCompleteTextView.CommaTokenizer())


        var edDate : EditText = findViewById(R.id.editTextDate)
        var c = Calendar.getInstance()
        edDate.setOnClickListener {
            DatePickerDialog(this, DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
                edDate.setText("$day/${month+1}/$year")
            },c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show()
        }


        var edTime : EditText = findViewById(R.id.editTextTime)
        edTime.setOnClickListener {
            TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { timePicker, hour, minutes ->
                edTime.setText("$hour : $minutes")
            },c.get(Calendar.HOUR), c.get(Calendar.MINUTE), true).show()
        }


        var btn : Button = findViewById(R.id.button)
        btn.setOnClickListener {
            Toast.makeText(applicationContext, "Sample Toast Messege", Toast.LENGTH_LONG).show()
        }
    }
}