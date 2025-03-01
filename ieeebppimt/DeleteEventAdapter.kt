package com.example.ieeebppimt

//import AdminEvent
import DeleteEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DeleteEventAdapter(
    private val eventList: List<DeleteEvent>, // List of DeleteEvent objects
    private val onDeleteClick: (DeleteEvent) -> Unit // Callback for delete action
) : RecyclerView.Adapter<DeleteEventAdapter.EventViewHolder>() {

    inner class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val eventTitle: TextView = itemView.findViewById(R.id.eventTitle)
        val eventDescription: TextView = itemView.findViewById(R.id.eventDescription)
        val eventOpenDate: TextView = itemView.findViewById(R.id.eventOpenDate)
        val eventCloseDate: TextView = itemView.findViewById(R.id.eventCloseDate)
        val deleteButton: Button = itemView.findViewById(R.id.deleteButton) // Delete button
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.delete_item_event, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = eventList[position]

        // Set event details in the respective views
        holder.eventTitle.text = event.title
        holder.eventDescription.text = event.description
        holder.eventOpenDate.text = "Opening Date: ${event.openDate}"
        holder.eventCloseDate.text = "Closing Date: ${event.closeDate}"

        // Always show the delete button for everyone
        holder.deleteButton.visibility = View.VISIBLE
        holder.deleteButton.setOnClickListener {
            onDeleteClick(event)  // Trigger delete callback
        }
    }

    override fun getItemCount(): Int {
        return eventList.size
    }
}
