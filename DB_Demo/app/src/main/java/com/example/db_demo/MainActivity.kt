package com.example.db_demo

import android.content.ContentValues
import android.content.DialogInterface
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.SearchView
import android.widget.SimpleAdapter
import android.widget.SimpleCursorAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog


@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    lateinit var ed_sname : EditText
    lateinit var ed_sem : EditText
    lateinit var btn_insert : Button
    lateinit var  btn_clear : Button
    lateinit var btn_update : Button
    lateinit var btn_delete : Button
    lateinit var btn_next : Button
    lateinit var btn_prev : Button
    lateinit var btn_first : Button
    lateinit var btn_last : Button
    lateinit var btn_showAll : Button
    lateinit var searchView: SearchView
    lateinit var listView: ListView
    lateinit var rs : Cursor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_insert = findViewById(R.id.btn_insert)
        btn_clear = findViewById(R.id.btn_clear)
        btn_update = findViewById(R.id.btn_update)
        btn_delete = findViewById(R.id.btn_delete)
        ed_sname = findViewById(R.id.ed_sname)
        ed_sem = findViewById(R.id.ed_sem)
        btn_next = findViewById(R.id.btn_next)
        btn_prev = findViewById(R.id.btn_prev)
        btn_first = findViewById(R.id.btn_first)
        btn_last = findViewById(R.id.btn_last)
        btn_showAll = findViewById(R.id.btn_show)
        searchView = findViewById(R.id.searchview)
        listView = findViewById(R.id.listview)

        var helper = MyDBHelper(applicationContext)
        var db = helper.writableDatabase
        Toast.makeText(applicationContext, "Database and Table Created", Toast.LENGTH_SHORT).show()

        rs = db.rawQuery("SELECT SID _id, SNAME, SEM FROM STUDENT",null)

        if(rs.moveToFirst()){
            ed_sname.setText(rs.getString(1))
            ed_sem.setText(rs.getString(2))
        }

        btn_insert.setOnClickListener {
            var cv = ContentValues()
            cv.put("SNAME",ed_sname.text.toString())
            cv.put("SEM",ed_sem.text.toString())
            db.insert("STUDENT",null,cv)
            rs = db.rawQuery("SELECT * FROM STUDENT",null)
            showMessage("Record Insert Successfully")
            clear()
        }

        btn_update.setOnClickListener {
            var cv = ContentValues()
            cv.put("SNAME",ed_sname.text.toString())
            cv.put("SEM",ed_sem.text.toString())
            db.update("STUDENT",cv,"SID = ?", arrayOf(rs.getString(0)))
            rs = db.rawQuery("SELECT * FROM STUDENT",null)
            showMessage("Record Update Successfully")
            clear()
        }

        btn_delete.setOnClickListener {
            db.delete("STUDENT","SID = ?", arrayOf(rs.getString(0)))
            rs = db.rawQuery("SELECT * FROM STUDENT",null)
            showMessage("Record Delete Successfully")
            clear()
        }

        btn_clear.setOnClickListener {
            clear()
        }

        btn_next.setOnClickListener {
            if (rs.moveToNext()){
                ed_sname.setText(rs.getString(1))
                ed_sem.setText(rs.getString(2))
            }
            else if (rs.moveToFirst()){
                ed_sname.setText(rs.getString(1))
                ed_sem.setText(rs.getString(2))
            }
            else{
                Toast.makeText(applicationContext,"Record Not Found", Toast.LENGTH_SHORT).show()
            }
        }

        btn_prev.setOnClickListener {
            if (rs.moveToPrevious()){
                ed_sname.setText(rs.getString(1))
                ed_sem.setText(rs.getString(2))
            }
            else if (rs.moveToLast()){
                ed_sname.setText(rs.getString(1))
                ed_sem.setText(rs.getString(2))
            }
            else{
                Toast.makeText(applicationContext,"Record Not Found", Toast.LENGTH_SHORT).show()
            }
        }

        btn_first.setOnClickListener {
            if (rs.moveToFirst()){
                ed_sname.setText(rs.getString(1))
                ed_sem.setText(rs.getString(2))
            }
            else{
                Toast.makeText(applicationContext,"Record Not Found", Toast.LENGTH_SHORT).show()
            }
        }

        btn_last.setOnClickListener {
            if (rs.moveToLast()){
                ed_sname.setText(rs.getString(1))
                ed_sem.setText(rs.getString(2))
            }
            else{
                Toast.makeText(applicationContext,"Record Not Found", Toast.LENGTH_SHORT).show()
            }
        }

        btn_showAll.setOnClickListener {

            searchView.isIconified = false
            searchView.queryHint =  "Select Among ${rs.count} Records"

            var adapter = SimpleCursorAdapter(
                applicationContext,
                R.layout.my_layout,
                rs,
                arrayOf("SNAME", "SEM"),
                intArrayOf(R.id.text1, R.id.text2)
            )
            listView.adapter = adapter

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(p0: String?): Boolean {
                    rs = db.rawQuery("SELECT SID _id, SNAME, SEM FROM STUDENT WHERE SNAME LIKE '%${p0}'",null)
                    adapter.changeCursor(rs)
                    return false
                }

            })
        }

    }



    private fun clear() {
        ed_sname.setText("")
        ed_sem.setText("")
        ed_sname.requestFocus()
    }

    private fun showMessage(s: String) {
        AlertDialog.Builder(this)
            .setTitle("Success!!")
            .setMessage(s)
            .setPositiveButton("OK", DialogInterface.OnClickListener { dialogInterface, i ->
                if(rs.moveToFirst()){
                    ed_sname.setText(rs.getString(1))
                    ed_sem.setText(rs.getString(2))
                }
                else{
                    Toast.makeText(applicationContext,"Record Not Found", Toast.LENGTH_SHORT).show()
                }
            }).show()
    }
}