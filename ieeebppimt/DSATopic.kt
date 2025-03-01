package com.example.ieeebppimt

data class DSATopic(
    val title: String,
    val questions: List<Pair<String, String>>, // Pair of question and solution link
    var isExpanded: Boolean = false // To track expansion
)
