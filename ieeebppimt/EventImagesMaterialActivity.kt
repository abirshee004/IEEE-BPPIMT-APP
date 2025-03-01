package com.example.ieeebppimt

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.net.Uri

class EventImagesMaterialActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_images_material)

        val downloadButton = findViewById<Button>(R.id.downloadButton)

        // Google Drive link to the folder with images
        val googleDriveLink = "https://drive.google.com/drive/u/3/folders/1ZadtyzRynOWLwn01Ll0MvR3tNcR6fVEO"

        // Open the Google Drive folder when the button is clicked
        downloadButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(googleDriveLink))
            startActivity(intent)
        }
    }
}
