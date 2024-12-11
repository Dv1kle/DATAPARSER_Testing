package com.example.dataparsing

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    private lateinit var listView: ListView
    private lateinit var editText: EditText
    private var currencyList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.currencyList)
        editText = findViewById(R.id.searchField)

        currencyList.add("Loading currencies...")

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, currencyList)
        listView.adapter = adapter

        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                adapter.filter.filter(s)
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        DataLoader(this, adapter).execute()
        currencyList.add("USD")

    }

}
