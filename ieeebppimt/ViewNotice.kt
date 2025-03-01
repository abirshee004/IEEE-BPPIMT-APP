package com.example.ieeebppimt

data class ViewNotice(
    val title: String,
    val description: String,
    val timestamp: Long,
    var isExpanded: Boolean = false
)
