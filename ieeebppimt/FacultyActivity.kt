package com.example.ieeebppimt

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.FirebaseFirestore


class FacultyActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var facultyList: ArrayList<Faculty>
    private lateinit var adapter: FacultyAdapter
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.faculty_activity)

        recyclerView = findViewById(R.id.facultyRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        facultyList = ArrayList()
        adapter = FacultyAdapter(this, facultyList)
        recyclerView.adapter = adapter

        db = FirebaseFirestore.getInstance()
        fetchFaculties()

        findViewById<FloatingActionButton>(R.id.addFacultyButton).setOnClickListener {
            startActivity(Intent(this, AddFacultyActivity::class.java))
        }
    }

    private fun fetchFaculties() {
        db.collection("Faculties").get().addOnSuccessListener { documents ->
            for (document in documents) {
                val faculty = document.toObject(Faculty::class.java)
                facultyList.add(faculty)
            }
            adapter.notifyDataSetChanged()
        }
    }
}
