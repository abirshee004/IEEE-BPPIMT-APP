package com.example.ieeebppimt

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ViewNoticesActivity : AppCompatActivity() {

    private lateinit var noticeRecyclerView: RecyclerView
    private val noticeList = mutableListOf<ViewNotice>()
    private lateinit var noticeAdapter: ViewNoticeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_notices)

        // Initialize RecyclerView
        noticeRecyclerView = findViewById(R.id.noticeRecyclerView)
        noticeRecyclerView.layoutManager = LinearLayoutManager(this)

        // Load Notices and set adapter
        loadNotices()
        noticeAdapter = ViewNoticeAdapter(noticeList)
        noticeRecyclerView.adapter = noticeAdapter
    }

    private fun loadNotices() {
        val sharedPreferences = getSharedPreferences("NoticePrefs", Context.MODE_PRIVATE)
        val gson = Gson()

        val noticeListJson = sharedPreferences.getString("notices", "[]") ?: "[]"
        val type = object : TypeToken<List<ViewNotice>>() {}.type
        val savedNotices: List<ViewNotice> = gson.fromJson(noticeListJson, type)

        noticeList.clear()
        noticeList.addAll(savedNotices)
    }
}
