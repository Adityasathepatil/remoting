package com.techtool.remoting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class explore_allinfo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explore_allinfo)

        val imgUrl = intent.getStringExtra("imgUrl")
        val description = intent.getStringExtra("description")
        val experience = intent.getStringExtra("experience")
        val role = intent.getStringExtra("role")

        // Now you have the data, you can update your UI accordingly
        // For example, set the text for TextViews, load image, etc.
        val imageView = findViewById<ImageView>(R.id.imgview)
        val experienceTextView = findViewById<TextView>(R.id.experience)
        val roleTextView = findViewById<TextView>(R.id.role)
        val descriptionTextView = findViewById<TextView>(R.id.description)

        // Update UI with retrieved data
        // (you might want to use a library like Picasso or Glide to load images from URLs)
        // Example:
         Picasso.get().load(imgUrl).into(imageView)
        experienceTextView.text = experience
        roleTextView.text = role
        descriptionTextView.text = description
    }
}