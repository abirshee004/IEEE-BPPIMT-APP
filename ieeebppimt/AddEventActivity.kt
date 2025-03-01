package com.example.ieeebppimt

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

// Ensure AdminEvent class is properly imported
data class AdminEvent(
    val title: String,
    val formLink: String,
    val description: String,
    val openDate: String,
    val closeDate: String
)

class AddEventActivity : AppCompatActivity() {
    private lateinit var eventTitleInput: EditText
    private lateinit var eventFormLinkInput: EditText
    private lateinit var eventDescriptionInput: EditText
    private lateinit var eventOpenDateInput: EditText
    private lateinit var eventCloseDateInput: EditText
    private lateinit var saveEventButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_event)

        // Initialize input fields
        eventTitleInput = findViewById(R.id.eventTitleInput)
        eventFormLinkInput = findViewById(R.id.eventFormLinkInput)
        eventDescriptionInput = findViewById(R.id.eventDescriptionInput)
        eventOpenDateInput = findViewById(R.id.eventOpenDateInput)
        eventCloseDateInput = findViewById(R.id.eventCloseDateInput)
        saveEventButton = findViewById(R.id.saveEventButton)

        saveEventButton.setOnClickListener {
            saveEventData()
        }
    }

    private fun saveEventData() {
        val eventTitle = eventTitleInput.text.toString().trim()
        val eventFormLink = eventFormLinkInput.text.toString().trim()
        val eventDescription = eventDescriptionInput.text.toString().trim()
        val eventOpenDate = eventOpenDateInput.text.toString().trim()
        val eventCloseDate = eventCloseDateInput.text.toString().trim()

        // Ensure no fields are empty
        if (eventTitle.isEmpty() || eventFormLink.isEmpty() || eventDescription.isEmpty() ||
            eventOpenDate.isEmpty() || eventCloseDate.isEmpty()) {
            Toast.makeText(this, "Please fill all fields!", Toast.LENGTH_SHORT).show()
            return
        }

        // Log captured inputs for debugging
        Log.d("AddEventActivity", "Event Title: $eventTitle")
        Log.d("AddEventActivity", "Event Form Link: $eventFormLink")
        Log.d("AddEventActivity", "Event Description: $eventDescription")
        Log.d("AddEventActivity", "Event Open Date: $eventOpenDate")
        Log.d("AddEventActivity", "Event Close Date: $eventCloseDate")

        val newEvent = AdminEvent(eventTitle, eventFormLink, eventDescription, eventOpenDate, eventCloseDate)

        // Save event and navigate to EventMaterialActivity
        saveEvent(newEvent)

        Toast.makeText(this, "Event Saved Successfully!", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, EventMaterialActivity::class.java))
        finish()
    }

    private fun saveEvent(event: AdminEvent) {
        val sharedPreferences = getSharedPreferences("EventPrefs", Context.MODE_PRIVATE)
        val gson = Gson()

        // Load existing events
        val eventListJson = sharedPreferences.getString("events", "[]") ?: "[]"
        val type = object : TypeToken<MutableList<AdminEvent>>() {}.type
        val eventList: MutableList<AdminEvent> = gson.fromJson(eventListJson, type)

        // Avoid duplicate events (optional check)
        if (eventList.any { it.title == event.title }) {
            Toast.makeText(this, "Event already exists!", Toast.LENGTH_SHORT).show()
            return
        }

        // Add the new event to the top
        eventList.add(0, event)

        // Save updated event list
        sharedPreferences.edit().apply {
            putString("events", gson.toJson(eventList))
            apply()
        }

        // Log saved event for verification
        Log.d("AddEventActivity", "Saved Event: ${gson.toJson(event)}")
    }
}
