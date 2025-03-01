package com.example.ieeebppimt

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DeleteNoticeAdapter(
    private val noticeList: MutableList<DeleteNotice>,
    private val onDeleteClick: (Int) -> Unit
) : RecyclerView.Adapter<DeleteNoticeAdapter.DeleteViewHolder>() {

    class DeleteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.deleteNoticeTitle)
        val deleteButton: Button = itemView.findViewById(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeleteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_delete_notice, parent, false)
        return DeleteViewHolder(view)
    }

    override fun onBindViewHolder(holder: DeleteViewHolder, position: Int) {
        val notice = noticeList[position]
        holder.title.text = notice.title

        // Handle delete click
        holder.deleteButton.setOnClickListener {
            onDeleteClick(position)
        }
    }

    override fun getItemCount(): Int = noticeList.size
}
