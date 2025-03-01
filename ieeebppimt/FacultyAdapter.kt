package com.example.ieeebppimt

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore

class FacultyAdapter(private val context: Context, private val facultyList: ArrayList<Faculty>) :
    RecyclerView.Adapter<FacultyAdapter.FacultyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FacultyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.faculty_item, parent, false)
        return FacultyViewHolder(view)
    }

    override fun onBindViewHolder(holder: FacultyViewHolder, position: Int) {
        val faculty = facultyList[position]
        holder.name.text = faculty.name
        holder.position.text = faculty.position

        // Load image using Glide
        Glide.with(context).load(faculty.imageUrl).placeholder(R.drawable.default_profile)
            .into(holder.image)

        // Handle menu click (Edit/Delete)
        holder.menu.setOnClickListener {
            val popup = PopupMenu(context, holder.menu)
            popup.inflate(R.menu.faculty_menu)
            popup.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.edit_faculty -> {
                        // Handle edit faculty
                        true
                    }
                    R.id.delete_faculty -> {
                        deleteFaculty(faculty, position)
                        true
                    }
                    else -> false
                }
            }
            popup.show()
        }
    }

    override fun getItemCount(): Int {
        return facultyList.size
    }

    class FacultyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.facultyImage)
        val name: TextView = itemView.findViewById(R.id.facultyName)
        val position: TextView = itemView.findViewById(R.id.facultyPosition)
        val menu: ImageView = itemView.findViewById(R.id.facultyMenu)
    }

    private fun deleteFaculty(faculty: Faculty, position: Int) {
        val db = FirebaseFirestore.getInstance()
        db.collection("Faculties").whereEqualTo("registrationId", faculty.registrationId)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    db.collection("Faculties").document(document.id).delete()
                        .addOnSuccessListener {
                            facultyList.removeAt(position)
                            notifyItemRemoved(position)
                        }
                }
            }
    }
}
