package com.example.ieeebppimt

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class StudyMaterialActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_study_material)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val studyMaterials = listOf(
            StudyMaterial("Android App PDF", "https://developer.android.com/courses/android-development-with-kotlin/course"),
            StudyMaterial("Compiler PDF", "https://media.licdn.com/dms/document/media/v2/D4D1FAQGR9sNxi4b3Xg/feedshare-document-pdf-analyzed/feedshare-document-pdf-analyzed/0/1683292169303?e=1741219200&v=beta&t=S-rijxBZkGhkofnQZDuGwvilte-h0o0ErhrTaAlSJ4U"),
            StudyMaterial("Data Structures PDF", "https://media.licdn.com/dms/document/media/v2/D4D1FAQHyZwbgd3PR_A/feedshare-document-pdf-analyzed/feedshare-document-pdf-analyzed/0/1715660104716?e=1741219200&v=beta&t=Kz-dkZGwLLMM_Q9xDfPp3_EqpfOLbVTBIC17x2YskfU"),
            StudyMaterial("Computer Networks PDF", "https://media.licdn.com/dms/document/media/v2/D561FAQEMKZIsoa8ddg/feedshare-document-pdf-analyzed/feedshare-document-pdf-analyzed/0/1725032266313?e=1741219200&v=beta&t=OP5ogsQl9oXbIYIvRj3_RECKv4nCoDZnrXVKPO4I0eQ")
        )

        val adapter = StudyMaterialAdapter(studyMaterials) { url ->
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }
        recyclerView.adapter = adapter

        // Apply Slide-Up Animation to RecyclerView
        try {
            val animation = AnimationUtils.loadLayoutAnimation(this, R.anim.layout_slide_up)
            recyclerView.layoutAnimation = animation
            recyclerView.scheduleLayoutAnimation()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
