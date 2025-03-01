package com.example.ieeebppimt

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class CodeSpaceActivity : AppCompatActivity() {

    private lateinit var cardDevelopment: CardView
    private lateinit var cardDSA: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_code_space)

        // Initialize the CardViews
        cardDevelopment = findViewById(R.id.cardDevelopment)
        cardDSA = findViewById(R.id.cardDSA)

        // Set click listeners for the CardViews
        cardDevelopment.setOnClickListener {
            // Start CodingLanguageActivity when clicking on Coding Language card
            val intent = Intent(this, DevelopmentActivity::class.java)
            startActivity(intent)
        }

        cardDSA.setOnClickListener {
            // Start DSAActivity when clicking on DSA card
            val intent = Intent(this, DSAActivity::class.java)
            startActivity(intent)
        }
    }
}
