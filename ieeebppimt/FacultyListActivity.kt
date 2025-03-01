package com.example.ieeebppimt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FacultyListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_faculty_list)

        val facultyRecyclerView: RecyclerView = findViewById(R.id.facultyRecyclerView)
        facultyRecyclerView.layoutManager = LinearLayoutManager(this)

        // Use FacultyList instead of Faculty
        val facultyList = listOf(
            FacultyList("Dr. Ananya Kanjilal", "HOD, CSE", R.drawable.dr_ananya_kanjilal), // Removed .toString()
            FacultyList("Dr. Bikromadittya Mondal", "IEEE President, CSE", R.drawable.dr_bikromadittya_mondal), // Removed .toString(), IT", R.drawable.faculty), // Removed .toString()
            FacultyList("Parthiv Modak", "Chairperson, CSE", R.drawable.parthiv),
            FacultyList("Sayak Sen", "Vice Chairperson, CSE", R.drawable.sayak),
            FacultyList("Sourasish Mondal", "Secretary, CSE", R.drawable.sourasish),
            FacultyList("Sankalpa Pramanik", "Treasurer, CSE", R.drawable.sankalpa),
            FacultyList("Abir Shee", "Core Member, CSE", R.drawable.abir)

        )

        facultyRecyclerView.adapter = FacultyListAdapter(facultyList) // Pass correct List<FacultyList>
    }
}
