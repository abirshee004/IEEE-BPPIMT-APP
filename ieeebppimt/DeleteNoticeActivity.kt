package com.example.ieeebppimt

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DeleteNoticeActivity : AppCompatActivity() {

    private lateinit var deleteRecyclerView: RecyclerView
    private val noticeList = mutableListOf<DeleteNotice>()
    private lateinit var deleteAdapter: DeleteNoticeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_notice)

        deleteRecyclerView = findViewById(R.id.deleteRecyclerView)
        deleteRecyclerView.layoutManager = LinearLayoutManager(this)

        loadNotices()

        deleteAdapter = DeleteNoticeAdapter(noticeList) { position ->
            deleteNotice(position)
        }

        deleteRecyclerView.adapter = deleteAdapter
    }

    // Load notices from SharedPreferences
    private fun loadNotices() {
        val sharedPreferences = getSharedPreferences("NoticePrefs", Context.MODE_PRIVATE)
        val gson = Gson()

        val noticeListJson = sharedPreferences.getString("notices", "[]") ?: "[]"
        val type = object : TypeToken<List<DeleteNotice>>() {}.type
        noticeList.clear()
        noticeList.addAll(gson.fromJson(noticeListJson, type))
    }

    // Delete the selected notice and update storage
    private fun deleteNotice(position: Int) {
        noticeList.removeAt(position)

        val sharedPreferences = getSharedPreferences("NoticePrefs", Context.MODE_PRIVATE)
        val gson = Gson()
        sharedPreferences.edit().apply {
            putString("notices", gson.toJson(noticeList))
            apply()
        }

        deleteAdapter.notifyItemRemoved(position)

        Toast.makeText(this, "Notice deleted!", Toast.LENGTH_SHORT).show()
    }
}
