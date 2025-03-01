package com.example.ieeebppimt

import DeleteEvent
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DeleteEventActivity : AppCompatActivity() {
    private lateinit var deleteEventRecyclerView: RecyclerView
    private lateinit var eventAdapter: DeleteEventAdapter
    private lateinit var eventList: MutableList<DeleteEvent> // Use DeleteEvent here
    private var userRole: String = "user" // Default to "user"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_event)

        // Get user role from SharedPreferences (assuming role is stored)
        val sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        userRole = sharedPreferences.getString("role", "user") ?: "user"

        // Initialize RecyclerView
        deleteEventRecyclerView = findViewById(R.id.deleteEventRecyclerView)
        deleteEventRecyclerView.layoutManager = LinearLayoutManager(this)

        // Load events from SharedPreferences
        loadEvents()

        // Set up adapter, passing the delete event callback
        eventAdapter = DeleteEventAdapter(eventList, ::deleteEvent) // Pass user role to adapter
        deleteEventRecyclerView.adapter = eventAdapter
    }

    private fun loadEvents() {
        val sharedPreferences = getSharedPreferences("EventPrefs", Context.MODE_PRIVATE)
        val gson = Gson()
        val eventListJson = sharedPreferences.getString("events", "[]") ?: "[]"

        // Convert JSON to Mutable List of DeleteEvent
        val type = object : TypeToken<MutableList<DeleteEvent>>() {}.type
        eventList = gson.fromJson(eventListJson, type)

        if (eventList.isEmpty()) {
            Toast.makeText(this, "No events available.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveEvents() {
        val sharedPreferences = getSharedPreferences("EventPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(eventList)
        editor.putString("events", json)
        editor.apply()
    }

    private fun deleteEvent(event: DeleteEvent) { // Use DeleteEvent here
        eventList.remove(event)  // Remove event from the list
        saveEvents()  // Save updated list
        eventAdapter.notifyDataSetChanged()  // Notify adapter to update UI
        Toast.makeText(this, "Event Deleted", Toast.LENGTH_SHORT).show()
    }
}
