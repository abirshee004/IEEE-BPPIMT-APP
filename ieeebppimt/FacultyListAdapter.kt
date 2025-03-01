package com.example.ieeebppimt

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.bumptech.glide.Glide // Optional for better image handling

class FacultyListAdapter(
    private val facultyList: List<FacultyList>
) : RecyclerView.Adapter<FacultyListAdapter.FacultyViewHolder>() {

    class FacultyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val facultyImage: ImageView = itemView.findViewById(R.id.facultyImage)
        val facultyName: TextView = itemView.findViewById(R.id.facultyName)
        val facultyPosition: TextView = itemView.findViewById(R.id.facultyPosition)
        val cardView: CardView = itemView.findViewById(R.id.cardView) // Ensure this exists in XML
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FacultyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_faculty, parent, false)
        return FacultyViewHolder(view)
    }

    override fun onBindViewHolder(holder: FacultyViewHolder, position: Int) {
        val faculty = facultyList[position]

        holder.facultyName.text = faculty.name
        holder.facultyPosition.text = faculty.position

        // ✅ Using setImageResource (if using local drawables)
        holder.facultyImage.setImageResource(faculty.imageResId)

        // ✅ OR Using Glide (if using remote images)
        // Glide.with(holder.itemView.context).load(faculty.imageResId).into(holder.facultyImage)

        // Click animation effect
        holder.cardView.setOnClickListener { view ->
            view.animate()
                .scaleX(0.95f)
                .scaleY(0.95f)
                .setDuration(100)
                .withEndAction {
                    view.animate().scaleX(1f).scaleY(1f).duration = 100
                }
        }
    }

    override fun getItemCount(): Int = facultyList.size
}
