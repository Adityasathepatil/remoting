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
import com.techtool.remoting.data.explore_data
import com.techtool.remoting.explore_allinfo


class explore_adapter(private val context: Context, private val explore_items: MutableList<explore_data>) :
    RecyclerView.Adapter<explore_adapter.exploreViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): exploreViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.explore_items, parent, false)
        return exploreViewHolder(view)
    }

    override fun onBindViewHolder(holder: exploreViewHolder, position: Int) {
        val currentItem = explore_items[position]


        holder.experience.text = currentItem.experience
        holder.role.text = currentItem.role

        Picasso.get().load(currentItem.icon).into(holder.imageView)
    }

    override fun getItemCount(): Int = explore_items.size

    inner class exploreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val currentItem = explore_items[position]
                    val intent = Intent(context, explore_allinfo::class.java).apply {
                        putExtra("imgUrl", currentItem.icon)
                        putExtra("description",currentItem.description)
                        putExtra("experience",currentItem.experience)
                        putExtra("role",currentItem.role)

                    }
                    context.startActivity(intent)
                }
            }
        }

        val imageView: ImageView = itemView.findViewById(R.id.icon)
        val experience : TextView = itemView.findViewById(R.id.experience)

        val role : TextView = itemView.findViewById(R.id.role)


    }
}