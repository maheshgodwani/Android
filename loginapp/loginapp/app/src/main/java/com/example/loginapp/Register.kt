package com.example.loginapp

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import java.util.*

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        var login : TextView = findViewById(R.id.login)
        var editSurname : EditText =findViewById(R.id.editSurname)
        var editName : EditText = findViewById(R.id.editName)
        var editEmail : EditText = findViewById(R.id.editEmail)
        var editPassword : EditText = findViewById(R.id.editPassword)
        var editdob : EditText = findViewById(R.id.editdob)
        var gender : RadioGroup = findViewById(R.id.ReadioGroup)
        var editCity : AutoCompleteTextView = findViewById(R.id.editCity)
        var btnregister : Button = findViewById(R.id.btnregister)
        var cal = Calendar.getInstance()

        var sp = application.getSharedPreferences("registerData", Context.MODE_PRIVATE)
        var editor = sp.edit()

        login.setOnClickListener {
            var i = Intent(this,Login::class.java)
            startActivity(i)
        }

        editdob.setOnClickListener {
            DatePickerDialog(this , DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
                editdob.setText("$day/${month+1}/$year")
//                Toast.makeText(applicationContext, "$day/${month+1}/$year", Toast.LENGTH_LONG).show()
            }, cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH)).show()
        }

        btnregister.setOnClickListener {
            val selectedGenderId = gender.checkedRadioButtonId
            val selectedGender = if (selectedGenderId != -1) {
                findViewById<RadioButton>(selectedGenderId).text.toString()
            } else {
                "Not Specified"
            }
            editor.putString("editSurname",editSurname.text.toString())
            editor.putString("editName",editName.text.toString())
            editor.putString("editEmail",editEmail.text.toString())
            editor.putString("editPassword",editPassword.text.toString())
            editor.putString("editdob",editdob.text.toString())
            editor.putString("gender",selectedGender)
            editor.putString("editCity",editCity.text.toString())

            Toast.makeText(applicationContext,"Data Saved!!",Toast.LENGTH_LONG).show()
            editor.commit()

            btnregister.setOnClickListener {
                val intent = Intent(this, DisplayActivity::class.java)
                // Pass current email as extra
                intent.putExtra("EMAIL", editEmail.text.toString())
                startActivity(intent)
            }


//            editSurname.setText("")
//            editName.setText("")
//            editEmail.setText("")
//            editPassword.setText("")
//            editdob.setText("")
//            gender.clearCheck()
//            editCity.setText("")
        }
    }
}