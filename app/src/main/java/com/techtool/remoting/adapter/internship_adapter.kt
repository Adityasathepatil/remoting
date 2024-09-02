package com.techtool.remoting.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.techtool.remoting.AllInfoActivity
import com.techtool.remoting.R
import com.techtool.remoting.data.internship_data
import com.techtool.remoting.data.recommanded_data

class internship_adapter(
    private val context: Context, private val internship_items: MutableList<internship_data>,
) :
    RecyclerView.Adapter<internship_adapter.internshipViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): internshipViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.internship_items, parent, false)
        return internshipViewHolder(view)
    }

    override fun onBindViewHolder(holder: internshipViewHolder, position: Int) {
        val currentItem = internship_items[position]

        holder.companyname.text = currentItem.title
        holder.role.text = currentItem.role
        holder.packag.text = currentItem.packag
        holder.fulltime.text = currentItem.fulltime
        holder.experince.text = currentItem.experience
        holder.location.text = currentItem.location
        Picasso.get().load(currentItem.icon).into(holder.imageView)
    }

    override fun getItemCount(): Int = internship_items.size

    inner class internshipViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val currentItem = internship_items[position]
                    val intent = Intent(context, AllInfoActivity::class.java).apply {
                        putExtra("imgUrl", currentItem.icon)
                        putExtra("title", currentItem.title)
                        putExtra("role", currentItem.role)
                        putExtra("description", currentItem.description) // Add description
                        putExtra("link", currentItem.link) // Add link
                        putExtra("fulltime", currentItem.fulltime)
                        putExtra("location", currentItem.location)
                        putExtra("experience", currentItem.experience)
                        // Add description and link here if available
                    }
                    context.startActivity(intent)
                }
            }
        }

        val imageView: ImageView = itemView.findViewById(R.id.company_logo)
        val companyname: TextView = itemView.findViewById(R.id.company_name)
        val role: TextView = itemView.findViewById(R.id.job_title)
        val packag: TextView = itemView.findViewById(R.id.packag)
        val experince: TextView = itemView.findViewById(R.id.experience)
        val location: TextView = itemView.findViewById(R.id.location)
        val fulltime: TextView = itemView.findViewById(R.id.anywhere)

    }


}