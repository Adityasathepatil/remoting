package com.techtool.remoting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.techtool.remoting.adapter.recommanded_adapter
import com.techtool.remoting.data.recommanded_data

class topinternship : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: recommanded_adapter
    private lateinit var recommandeditems: MutableList<recommanded_data>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topinternship)


        recyclerView = findViewById(R.id.topinternshiprec)




        recommandeditems = mutableListOf<recommanded_data>()


        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = layoutManager

        adapter = recommanded_adapter(this,recommandeditems)
        recyclerView.adapter = adapter
        // Retrieve data from Firebase and populate the adapter
        val databaseReference = FirebaseDatabase.getInstance().reference.child("topinternship")
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                recommandeditems.clear()
                for (itemSnapshot in snapshot.children) {
                    val item = itemSnapshot.getValue(recommanded_data::class.java)
                    item?.let { recommandeditems.add(it) }
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("HomeFragment", "Failed to read value.", error.toException())
            }
        })


    }
    }
