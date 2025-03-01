package com.example.ieeebppimt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DSAActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dsa)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewDSA)

        val topics = listOf(
            DSATopic(
                "Arrays",
                listOf(
                    "Find Missing Number" to "https://example.com/array1",
                    "Largest Sum Subarray" to "https://example.com/array2"
                )
            ),
            DSATopic(
                "LinkedList",
                listOf(
                    "Reverse a LinkedList" to "https://example.com/ll1",
                    "Detect Cycle" to "https://example.com/ll2"
                )
            ),
            DSATopic(
                "Dynamic Programming",
                listOf(
                    "0/1 Knapsack" to "https://example.com/dp1",
                    "Longest Common Subsequence" to "https://example.com/dp2"
                )
            )
        )

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = DSAAdapter(this, topics)
        recyclerView.addItemDecoration(SpaceItemDecoration(16))
    }
}
