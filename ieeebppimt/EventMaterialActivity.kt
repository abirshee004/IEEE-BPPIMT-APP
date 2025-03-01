package com.example.ieeebppimt

import Event
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class EventMaterialActivity : AppCompatActivity() {
    private lateinit var eventRecyclerView: RecyclerView
    private lateinit var eventAdapter: EventAdapter
    private lateinit var eventList: MutableList<Event>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_material)

        eventRecyclerView = findViewById(R.id.eventRecyclerView)

        // Load events from SharedPreferences
        loadEvents()

        eventAdapter = EventAdapter(eventList)
        eventRecyclerView.layoutManager = LinearLayoutManager(this)
        eventRecyclerView.adapter = eventAdapter
    }

    private fun loadEvents() {
        val sharedPreferences = getSharedPreferences("EventPrefs", Context.MODE_PRIVATE)
        val gson = Gson()
        val eventListJson = sharedPreferences.getString("events", "[]")

        // Convert JSON to Mutable List of Event
        val type = object : TypeToken<MutableList<Event>>() {}.type
        eventList = gson.fromJson(eventListJson, type)

        if (eventList.isEmpty()) {
            Toast.makeText(this, "No events available.", Toast.LENGTH_SHORT).show()
        }
    }
}

