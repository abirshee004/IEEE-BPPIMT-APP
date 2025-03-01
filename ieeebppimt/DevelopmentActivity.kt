package com.example.ieeebppimt

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import android.widget.SimpleAdapter

class DevelopmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_development)

        val listView: ListView = findViewById(R.id.listViewCoding)

        // Title and Subtitle Pairs
        val topics = listOf(
            "C Language" to "Basics of C Programming",
            "Arrays" to "Linear Data Structure",
            "Stack" to "LIFO Data Structure",
            "LinkedList" to "Dynamic Data Structure",
            "Trees" to "Hierarchical Data Structure",
            "Graphs" to "Network-Based Structure"
        )

        // Mapping Data for Two-Line Display
        val data = topics.map { mapOf("title" to it.first, "subtitle" to it.second) }

        val adapter = SimpleAdapter(
            this,
            data,
            android.R.layout.simple_list_item_2,
            arrayOf("title", "subtitle"),
            intArrayOf(android.R.id.text1, android.R.id.text2)
        )

        listView.adapter = adapter
    }
}
