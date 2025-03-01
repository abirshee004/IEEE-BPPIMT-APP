package com.example.ieeebppimt

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class UploadNotice : AppCompatActivity() {

    private lateinit var noticeTitle: EditText
    private lateinit var noticeDescription: EditText
    private lateinit var uploadNoticeBtn: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_notice)

        // Initialize Views
        noticeTitle = findViewById(R.id.notice_title)
        noticeDescription = findViewById(R.id.notice_description)
        uploadNoticeBtn = findViewById(R.id.upload_notice_btn)

        // Upload Notice Button Click
        uploadNoticeBtn.setOnClickListener {
            val title = noticeTitle.text.toString().trim()
            val description = noticeDescription.text.toString().trim()

            if (title.isEmpty()) {
                noticeTitle.error = "Please enter notice title"
                return@setOnClickListener
            }

            if (description.isEmpty()) {
                noticeDescription.error = "Please enter notice description"
                return@setOnClickListener
            }

            saveNotice(Notice(title, description, System.currentTimeMillis()))

            Toast.makeText(this, "Notice Uploaded!", Toast.LENGTH_SHORT).show()

            // Navigate to ViewNoticesActivity
            startActivity(Intent(this, ViewNoticesActivity::class.java))
            finish()
        }
    }

    // Save notice to SharedPreferences using JSON
    private fun saveNotice(notice: Notice) {
        val sharedPreferences = getSharedPreferences("NoticePrefs", Context.MODE_PRIVATE)
        val gson = Gson()

        // Fetch existing notices or empty list
        val noticeListJson = sharedPreferences.getString("notices", "[]") ?: "[]"
        val type = object : TypeToken<MutableList<Notice>>() {}.type
        val noticeList: MutableList<Notice> = gson.fromJson(noticeListJson, type)

        // Add new notice at the top
        noticeList.add(0, notice)

        // Save updated notice list
        sharedPreferences.edit().apply {
            putString("notices", gson.toJson(noticeList))
            apply()
        }
    }

    // Data class to hold Notice details
    data class Notice(
        val title: String,
        val description: String,
        val timestamp: Long
    )
}
