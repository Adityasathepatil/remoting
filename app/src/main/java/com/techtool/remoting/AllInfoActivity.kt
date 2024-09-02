package com.techtool.remoting

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.squareup.picasso.Picasso

class AllInfoActivity : AppCompatActivity() {
    private lateinit var adView: AdView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_info)

        window.decorView.setBackgroundColor(getColor(R.color.white))
        adView = findViewById(R.id.adView)

        MobileAds.initialize(this) {}
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)

        // Retrieve data from the intent
        val imgUrl = intent.getStringExtra("imgUrl")
        val title = intent.getStringExtra("title")
        val role = intent.getStringExtra("role")
        val description = intent.getStringExtra("description")
        val link = intent.getStringExtra("link")
        val fulltime = intent.getStringExtra("fulltime")
        val location = intent.getStringExtra("location")
        val experience = intent.getStringExtra("experience")

        // Now you have the data, you can update your UI accordingly
        // For example, set the text for TextViews, load image, etc.
        val imageView = findViewById<ImageView>(R.id.imgview)
        val companyname = findViewById<TextView>(R.id.company_name)
        val roleTextView = findViewById<TextView>(R.id.role)
        val packagTextView = findViewById<TextView>(R.id.packag)
        val experienceTextView = findViewById<TextView>(R.id.experience)
        val locationTextView = findViewById<TextView>(R.id.location)
        val fulltimeTextView = findViewById<TextView>(R.id.fulltime)
        val descriptionTextView = findViewById<TextView>(R.id.description)


        // Update UI with retrieved data
        // (you might want to use a library like Picasso or Glide to load images from URLs)
        // Example:
         Picasso.get().load(imgUrl).into(imageView)
        companyname.text = title
        roleTextView.text ="domain name->  "+ role
        packagTextView.text ="link->  "+ link
        experienceTextView.text ="experience->  "+ experience
        locationTextView.text ="location->  "+ location
        fulltimeTextView.text ="fulltime/internship->  "+ fulltime
        descriptionTextView.text = "description->  "+ description

        packagTextView.setOnClickListener {
            // Open the link in a web browser
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
            startActivity(browserIntent)
        }
    }
}
