package com.example.ieeebppimt

data class NoticeData(
    var title: String = "",
    var image: String = "",
    var date: String = "",
    var time: String = "",
    var key: String = ""  // This key will hold the unique Firebase key
)
