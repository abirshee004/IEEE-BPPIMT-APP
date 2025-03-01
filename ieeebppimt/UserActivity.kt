package com.example.ieeebppimt

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class UserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        val clickAnim = AnimationUtils.loadAnimation(this, R.anim.click_animation)

        val viewNoticesCard = findViewById<CardView>(R.id.viewNoticesCard)
        val facultyListCard = findViewById<CardView>(R.id.facultyListCard)
        val studyMaterialCard = findViewById<CardView>(R.id.studyMaterialCard)
        val eventImageMaterialCard = findViewById<CardView>(R.id.eventimagesCard)
        val codeSpaceCard = findViewById<CardView>(R.id.CodeSpaceCard)
        val eventMaterialCard = findViewById<CardView>(R.id.eventMaterialCard)

        // Open the Notice Board Activity
        viewNoticesCard.setOnClickListener {
            it.startAnimation(clickAnim)
            startActivity(Intent(this, ViewNoticesActivity::class.java))
        }

        // Open the Faculty List Activity
        facultyListCard.setOnClickListener {
            it.startAnimation(clickAnim)
            startActivity(Intent(this, FacultyListActivity::class.java))
        }

        // Open the Study Material Activity
        studyMaterialCard.setOnClickListener {
            it.startAnimation(clickAnim)
            startActivity(Intent(this, StudyMaterialActivity::class.java))
        }

        // Open the Event Images Activity
        eventMaterialCard.setOnClickListener {
            it.startAnimation(clickAnim)
            startActivity(Intent(this, EventMaterialActivity::class.java))
        }

        // Open the Event Images Activity
        eventImageMaterialCard.setOnClickListener {
            it.startAnimation(clickAnim)
            startActivity(Intent(this, EventImagesMaterialActivity::class.java))
        }

        codeSpaceCard.setOnClickListener{
            it.startAnimation(clickAnim)
            startActivity(Intent(this, CodeSpaceActivity::class.java))
        }
    }
}
