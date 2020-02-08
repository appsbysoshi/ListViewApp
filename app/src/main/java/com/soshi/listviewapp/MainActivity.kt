package com.soshi.listviewapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        var sortType = 1 //1:IDの昇順, 2:NAMEの昇順
        val presenter = MainPresenter(this)
        var userArrayList = presenter.getUserArrayList(sortType)

        val listView = findViewById<ListView>(R.id.list_view)
        val adapter =  ListViewAdapter(this, userArrayList)
        listView.adapter = adapter

        val sortTypeText = findViewById<TextView>(R.id.sort_type_text)
        sortTypeText.text = getString(R.string.sort_type) + sortType

        val sortBtn = findViewById<Button>(R.id.sort_btn)
        sortBtn.setOnClickListener{
            sortType = when (sortType) {
                1 -> 2
                else -> 1
            }
            userArrayList = presenter.getUserArrayList(sortType)
            adapter.updateListView(userArrayList)
            sortTypeText.text = getString(R.string.sort_type) + sortType
        }
    }
}