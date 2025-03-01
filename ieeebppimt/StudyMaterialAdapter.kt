package com.example.ieeebppimt

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView

class StudyMaterialAdapter(
    private val materials: List<StudyMaterial>,
    private val onItemClick: (String) -> Unit
) : RecyclerView.Adapter<StudyMaterialAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.materialTitle)
        val cardView: MaterialCardView = itemView.findViewById(R.id.cardView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_study_material, parent, false)

        // Fade-in animation when the item loads
        view.alpha = 0f
        view.animate().alpha(1f).setDuration(500).start()

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val material = materials[position]
        holder.title.text = material.title

        // Click animation: Slight shrink effect
        holder.cardView.setOnClickListener { view ->
            view.animate()
                .scaleX(0.95f)
                .scaleY(0.95f)
                .setDuration(100)
                .withEndAction {
                    view.animate().scaleX(1f).scaleY(1f).duration = 100
                    onItemClick(material.url)
                }
        }
    }

    override fun getItemCount(): Int = materials.size
}
