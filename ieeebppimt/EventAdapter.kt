package com.example.ieeebppimt

import Event
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class EventAdapter(private val eventList: List<Event>) : RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    inner class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val eventTitle: TextView = itemView.findViewById(R.id.eventTitle)
        val eventDescription: TextView = itemView.findViewById(R.id.eventDescription)
        val eventOpenDate: TextView = itemView.findViewById(R.id.eventOpenDate)
        val eventCloseDate: TextView = itemView.findViewById(R.id.eventCloseDate)
        val openFormButton: Button = itemView.findViewById(R.id.openFormButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_event, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = eventList[position]

        holder.eventTitle.text = event.title
        holder.eventDescription.text = event.description
        holder.eventOpenDate.text = "Opening Date: ${event.openDate}"
        holder.eventCloseDate.text = "Closing Date: ${event.closeDate}"

        // Handle the button click properly
        holder.openFormButton.setOnClickListener {
            event.formLink?.let { formLink ->
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(formLink))
                it.context.startActivity(intent)
            } ?: run {
                // Handle case when formLink is null
                Toast.makeText(it.context, "Form link not available", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun getItemCount(): Int {
        return eventList.size
    }
}

