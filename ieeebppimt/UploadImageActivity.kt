package com.example.ieeebppimt

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.cardview.widget.CardView
import com.bumptech.glide.Glide

class UploadImageActivity : AppCompatActivity() {
    private lateinit var imagePreview: ImageView
    private lateinit var imagePreviewCard: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Force Light Mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        setContentView(R.layout.activity_upload_image)

        // Initialize UI Elements
        val openDriveButton: Button = findViewById(R.id.openDriveButton)
        imagePreview = findViewById(R.id.imagePreview)
        imagePreviewCard = findViewById(R.id.imagePreviewCard)

        // Load last uploaded image (Optional)
        loadLastUploadedImage()

        // Button Click - Open Google Drive
        openDriveButton.setOnClickListener {
            openGoogleDrive()
        }
    }

    private fun openGoogleDrive() {
        try {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://drive.google.com/drive/u/3/folders/1o9J6fllnesuMRstA2YbQ4t-cWFGvlupv") // Google Drive URL
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Log.e("UploadImageActivity", "Google Drive app not found", e)
        }
    }

    private fun loadLastUploadedImage() {
        // Load the last uploaded image from a URL (Example URL)
        val lastImageUrl = "https://example.com/last_uploaded_image.jpg"

        // Show Image Preview if URL is available
        if (lastImageUrl.isNotEmpty()) {
            imagePreviewCard.visibility = android.view.View.VISIBLE
            Glide.with(this).load(lastImageUrl).into(imagePreview)
        }
    }
}
