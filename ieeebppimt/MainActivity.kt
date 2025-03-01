package com.example.ieeebppimt

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import android.util.Log
import com.google.android.material.card.MaterialCardView

class MainActivity : AppCompatActivity() {
    private var uploadNoticeCard: MaterialCardView? = null
    private var facultyActivityCard: MaterialCardView? = null
    private var addEventCard: MaterialCardView? = null
    private var deleteEventCard: MaterialCardView? = null
    private var uploadImageCard: MaterialCardView? = null
    private var deleteNoticeCard: MaterialCardView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Force Light Mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        setContentView(R.layout.activity_main)

        // Initialize "Upload Notice" card
        try {
            uploadNoticeCard = findViewById(R.id.add_notice_button)
            uploadNoticeCard?.setOnClickListener {
                val intent = Intent(this, UploadNotice::class.java)
                startActivity(intent)
            }
        } catch (e: Exception) {
            Log.e("MainActivity", "Error initializing upload notice card", e)
        }

        // Initialize "Faculty Activity" card
        facultyActivityCard = findViewById(R.id.add_faculty)
        facultyActivityCard?.setOnClickListener {
            val intent = Intent(this, FacultyActivity::class.java)
            startActivity(intent)
        }

        // Initialize "Add Event" card
        addEventCard = findViewById(R.id.add_event)
        addEventCard?.setOnClickListener {
            val intent = Intent(this, AddEventActivity::class.java)
            startActivity(intent)
        }

        // Initialize "Delete Event" card (only visible to admins)
        deleteEventCard = findViewById(R.id.delete_event)
        deleteEventCard?.setOnClickListener {
            val intent = Intent(this, DeleteEventActivity::class.java)
            startActivity(intent)
        }

        // Initialize "Upload Image" card
        uploadImageCard = findViewById(R.id.add_image)
        uploadImageCard?.setOnClickListener {
            val intent = Intent(this, UploadImageActivity::class.java)
            startActivity(intent)
        }

        // Initialize "Delete Notice" card
        deleteNoticeCard = findViewById(R.id.add_delete_notice)
        deleteNoticeCard?.setOnClickListener {
            val intent = Intent(this, DeleteNoticeActivity::class.java)
            startActivity(intent)
        }

    }
}