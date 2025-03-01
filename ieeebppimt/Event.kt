data class Event(
    val title: String,
    val description: String,
    val openDate: String,
    val closeDate: String,
    val formLink: String? // Make sure this is nullable if the form link can be missing
)
