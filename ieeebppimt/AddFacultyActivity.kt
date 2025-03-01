package com.example.ieeebppimt

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.io.InputStream

class AddFacultyActivity : AppCompatActivity() {

    private lateinit var facultyProfileImage: ImageView
    private lateinit var themeSwitch: Switch
    private lateinit var preferences: SharedPreferences
    private val imageRequestCode = 1001
    private var selectedImageUri: Uri? = null
    private val storageReference = FirebaseStorage.getInstance().reference
    private val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().reference

    override fun onCreate(savedInstanceState: Bundle?) {
        // Load the saved theme mode
//        preferences = getSharedPreferences("theme_prefs", MODE_PRIVATE)
//        val savedThemeMode = preferences.getInt("theme_mode", AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
//        AppCompatDelegate.setDefaultNightMode(savedThemeMode)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_faculty_activity)

        facultyProfileImage = findViewById(R.id.facultyProfileImage)
        themeSwitch = findViewById(R.id.themeSwitch)

        // Set switch state based on saved theme
//        themeSwitch.isChecked = savedThemeMode == AppCompatDelegate.MODE_NIGHT_YES
//        themeSwitch.setOnCheckedChangeListener { _, isChecked ->
//            val newMode = if (isChecked) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
//            AppCompatDelegate.setDefaultNightMode(newMode)
//            saveThemeMode(newMode)
//        }

        // Open gallery on image click
        facultyProfileImage.setOnClickListener {
            openGallery()
        }

        findViewById<Button>(R.id.saveFacultyButton).setOnClickListener {
            saveFacultyData()
        }
    }

    private fun saveThemeMode(mode: Int) {
        preferences.edit().putInt("theme_mode", mode).apply()
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, imageRequestCode)
    }

    @Deprecated("This method has been deprecated in favor of using the Activity Result API\n      which brings increased type safety via an {@link ActivityResultContract} and the prebuilt\n      contracts for common intents available in\n      {@link androidx.activity.result.contract.ActivityResultContracts}, provides hooks for\n      testing, and allow receiving results in separate, testable classes independent from your\n      activity. Use\n      {@link #registerForActivityResult(ActivityResultContract, ActivityResultCallback)}\n      with the appropriate {@link ActivityResultContract} and handling the result in the\n      {@link ActivityResultCallback#onActivityResult(Object) callback}.")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == imageRequestCode && resultCode == RESULT_OK) {
            selectedImageUri = data?.data
            Glide.with(this).load(selectedImageUri).into(facultyProfileImage)
        }
    }

    private fun saveFacultyData() {
        val name = findViewById<TextInputEditText>(R.id.facultyNameInput).text.toString()
        val position = findViewById<TextInputEditText>(R.id.facultyPositionInput).text.toString()
        val registrationId = findViewById<TextInputEditText>(R.id.facultyRegIdInput).text.toString()
        val stream = findViewById<TextInputEditText>(R.id.facultyStreamInput).text.toString()
        val year = findViewById<TextInputEditText>(R.id.facultyYearInput).text.toString()
        val rollNo = findViewById<TextInputEditText>(R.id.facultyRollNoInput).text.toString()

        if (name.isBlank() || position.isBlank() || registrationId.isBlank()) {
            Toast.makeText(this, "Please fill all required fields!", Toast.LENGTH_SHORT).show()
            return
        }

        lifecycleScope.launch {
            val imageUrl = selectedImageUri?.let { uploadImage(it) }

            val facultyData = mapOf(
                "name" to name,
                "position" to position,
                "registration_id" to registrationId,
                "stream" to stream,
                "year" to year,
                "roll_no" to rollNo,
                "profile_image" to imageUrl
            )

            try {
                val newFacultyRef = databaseReference.child("faculties").push()
                newFacultyRef.setValue(facultyData)
                    .addOnSuccessListener {
                        Toast.makeText(this@AddFacultyActivity, "Faculty added successfully!", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(this@AddFacultyActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
            } catch (e: Exception) {
                runOnUiThread {
                    Toast.makeText(this@AddFacultyActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private suspend fun uploadImage(uri: Uri): String? {
        return try {
            val inputStream: InputStream? = contentResolver.openInputStream(uri)
            val fileName = "faculty_images/${System.currentTimeMillis()}.jpg"
            val imageRef = storageReference.child(fileName)

            val uploadTask = imageRef.putStream(inputStream!!)
            val uploadResult = uploadTask.await()

            if (uploadResult != null) {
                val downloadUrl = imageRef.downloadUrl.await()
                downloadUrl.toString()
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }
}
