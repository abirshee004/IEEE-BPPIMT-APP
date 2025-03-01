package com.example.ieeebppimt

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class DSAAdapter(
    private val context: Context,
    private val topics: List<DSATopic>
) : RecyclerView.Adapter<DSAAdapter.DSAViewHolder>() {

    inner class DSAViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.textTitle)
        val arrowIcon: ImageView = itemView.findViewById(R.id.arrowIcon)
        val questionsLayout: LinearLayout = itemView.findViewById(R.id.questionsLayout)
        val cardView: CardView = itemView.findViewById(R.id.cardViewItem) // Ensure ID exists
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DSAViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_dsa_topic, parent, false)
        return DSAViewHolder(view)
    }

    override fun onBindViewHolder(holder: DSAViewHolder, position: Int) {
        val topic = topics[position]
        holder.title.text = topic.title

        // Populate questions dynamically
        holder.questionsLayout.removeAllViews()
        topic.questions.forEach { (question, link) ->
            val questionView = TextView(context).apply {
                text = question
                textSize = 16f
                setPadding(16, 8, 16, 8) // Space between questions
                setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
                    context.startActivity(intent)
                }
            }
            holder.questionsLayout.addView(questionView)
        }

        // Handle expand/collapse
        holder.arrowIcon.setImageResource(
            if (topic.isExpanded) R.drawable.ic_arrow_up else R.drawable.ic_arrow_down
        )

        // Toggle visibility
        holder.questionsLayout.visibility = if (topic.isExpanded) View.VISIBLE else View.GONE

        // Dynamically add top margin when expanded
        val params = holder.cardView.layoutParams as ViewGroup.MarginLayoutParams
        params.topMargin = if (topic.isExpanded) 32 else 8 // Increase margin on expand
        holder.cardView.layoutParams = params
        holder.cardView.requestLayout()

        // Toggle expansion
        holder.itemView.setOnClickListener {
            topic.isExpanded = !topic.isExpanded
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int = topics.size
}
