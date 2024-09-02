package com.techtool.remoting.UI

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.techtool.remoting.R
import com.techtool.remoting.adapter.recommanded_adapter
import com.techtool.remoting.data.recommanded_data
import com.techtool.remoting.topinternship
import com.techtool.remoting.topjob

class home : Fragment() {


    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: recommanded_adapter
    private lateinit var recommandeditems: MutableList<recommanded_data>
    private lateinit var firstcardview : CardView
    private lateinit var secondcd : CardView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        recyclerView = view.findViewById(R.id.recyclerviewRecommanded)
        firstcardview= view.findViewById(R.id.firstcardview)
        secondcd = view.findViewById(R.id.secondcd)

        firstcardview.setOnClickListener {
            val intent = Intent(requireContext(), topjob::class.java)
            startActivity(intent)
        }

        secondcd.setOnClickListener {
            val i = Intent(requireContext(), topinternship::class.java)
            startActivity(i)
        }


        recommandeditems = mutableListOf<recommanded_data>()


        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = layoutManager

        adapter = recommanded_adapter(requireContext(),recommandeditems)
        recyclerView.adapter = adapter
        // Retrieve data from Firebase and populate the adapter
        val databaseReference = FirebaseDatabase.getInstance().reference.child("recommanded")
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



        return view
    }
}