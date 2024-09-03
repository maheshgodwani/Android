package com.example.contectsapp

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    companion object {
        private const val REQUEST_CODE_READ_CONTACTS = 111
        private const val REQUEST_CODE_CALL_PHONE = 112
        private const val REQUEST_CODE_ADD_CONTACT = 113
    }

    private val cols = arrayOf(
        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
        ContactsContract.CommonDataKinds.Phone.NUMBER,
        ContactsContract.CommonDataKinds.Phone._ID
    )

    private lateinit var listView: ListView
    private lateinit var searchView: SearchView
    private lateinit var adapter: SimpleCursorAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.listview)
        searchView = findViewById(R.id.search_view)

        val btnAdd: FloatingActionButton = findViewById(R.id.btn_add)

        btnAdd.setOnClickListener {
            val intent = Intent(Intent.ACTION_INSERT)
            intent.type = ContactsContract.Contacts.CONTENT_TYPE

            // Optional: Add initial data to the contact
            intent.putExtra(ContactsContract.Intents.Insert.NAME, "")
            intent.putExtra(ContactsContract.Intents.Insert.PHONE, "")

            startActivity(intent)

        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { filterContacts(it) }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { filterContacts(it) }
                return true
            }
        })

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_CONTACTS), REQUEST_CODE_READ_CONTACTS)
        } else {
            setupContactsList()
        }
    }

    override fun onResume() {
        super.onResume()
        // Refresh contacts list when the activity resumes
        setupContactsList()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_READ_CONTACTS && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            setupContactsList()
        } else if (requestCode == REQUEST_CODE_CALL_PHONE && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // Permission for CALL_PHONE granted
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_ADD_CONTACT) {
            setupContactsList()
        }
    }

    private fun setupContactsList() {
        val rs = contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            cols,
            null,
            null,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
        )

        adapter = SimpleCursorAdapter(
            this,
            android.R.layout.simple_list_item_2,
            rs,
            cols,
            intArrayOf(android.R.id.text1, android.R.id.text2),
            0
        )

        listView.adapter = adapter

        // Handle item clicks
        listView.setOnItemClickListener { _, _, position, _ ->
            val cursor = listView.adapter.getItem(position) as android.database.Cursor
            val numberColumnIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
            if (numberColumnIndex != -1) {
                val phoneNumber = cursor.getString(numberColumnIndex)
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), REQUEST_CODE_CALL_PHONE)
                } else {
                    makeCall(phoneNumber)
                }
            } else {
                // Handle the case where the column index is not found
                Toast.makeText(this, "Error: Column index not found", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun filterContacts(query: String) {
        val selection = "${ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME} LIKE ?"
        val selectionArgs = arrayOf("%$query%")

        val rs = contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            cols,
            selection,
            selectionArgs,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
        )

        adapter.changeCursor(rs)
    }

    private fun makeCall(phoneNumber: String) {
        val callIntent = Intent(Intent.ACTION_CALL)
        callIntent.data = Uri.parse("tel:$phoneNumber")
        startActivity(callIntent)
    }
}
