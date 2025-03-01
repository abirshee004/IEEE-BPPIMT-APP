package com.example.ieeebppimt

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ViewNoticeAdapter(private val noticeList: MutableList<ViewNotice>) :
    RecyclerView.Adapter<ViewNoticeAdapter.NoticeViewHolder>() {

    class NoticeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.noticeTitle)
        val description: TextView = itemView.findViewById(R.id.noticeDescription)
        val timestamp: TextView = itemView.findViewById(R.id.noticeTimestamp)
        val arrowIcon: ImageView = itemView.findViewById(R.id.arrowIcon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_notice, parent, false)
        return NoticeViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoticeViewHolder, position: Int) {
        val notice = noticeList[position]

        holder.title.text = notice.title
        holder.description.text = notice.description
        holder.timestamp.text = "Posted on: ${formatDate(notice.timestamp)}"

        // Toggle visibility
        holder.description.visibility = if (notice.isExpanded) View.VISIBLE else View.GONE
        holder.timestamp.visibility = if (notice.isExpanded) View.VISIBLE else View.GONE

        // Rotate arrow based on expansion
        holder.arrowIcon.rotation = if (notice.isExpanded) 90f else 0f

        // Click listener for expanding/collapsing
        holder.itemView.setOnClickListener {
            notice.isExpanded = !notice.isExpanded
            notifyItemChanged(position)
        }
    }

    override fun getItemCount() = noticeList.size

    private fun formatDate(timestamp: Long): String {
        val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        return sdf.format(Date(timestamp))
    }
}
